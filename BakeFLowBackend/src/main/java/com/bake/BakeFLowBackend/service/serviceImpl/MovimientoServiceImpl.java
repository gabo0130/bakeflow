package com.bake.BakeFLowBackend.service.serviceImpl;

import com.bake.BakeFLowBackend.dto.request.MovimientoRequest;
import com.bake.BakeFLowBackend.entity.Movimiento;
import com.bake.BakeFLowBackend.entity.Producto;
import com.bake.BakeFLowBackend.entity.TipoMovimiento;
import com.bake.BakeFLowBackend.entity.Usuario;
import com.bake.BakeFLowBackend.repository.MovimientoRepository;
import com.bake.BakeFLowBackend.service.MovimientoService;
import com.bake.BakeFLowBackend.service.UsuarioService;
import com.bake.BakeFLowBackend.util.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    UsuarioServiceImpl usuarioService;

    @Autowired
    ProductoServiceImpl productoService;

    @Autowired
    TipoMovimientoServiceImpl tipoMovimientoService;

    @Autowired
    Operation operation;

    @Override
    public void registrarMovimiento(Movimiento movimiento) {
        movimientoRepository.save(movimiento);
    }

    @Override
    public void registrarMovimiento(MovimientoRequest request) {
        Movimiento movimiento = new Movimiento();
        Producto producto = new Producto();
        producto.setId(request.getProductoId());

        movimiento.setProducto(producto);
        movimiento.setCantidad(request.getCantidad());

        if (request.getCostoUnitario()!= null) {
            movimiento.setCostoUnitario(request.getCostoUnitario());
            movimiento.setCostoTotal(request.getCostoUnitario() * request.getCantidad());
        }

        if(request.getCostoTotal() != null){
            movimiento.setCostoTotal(request.getCostoTotal());
            movimiento.setCostoUnitario(request.getCostoTotal() / request.getCantidad());
        }


        TipoMovimiento tipoMovimiento = tipoMovimientoService.obtenerTipoMovimiento(request.getTipoMovimientoId());
        if(operation.esResta(tipoMovimiento.getOperacion())){
            if (!productoService.canBeMinus(producto.getId(),request.getCantidad()))
                throw new IllegalArgumentException(String.format("No se puede realizar la operación de %s, ya que la cantidad a restar es mayor a la existencia",tipoMovimiento.getNombre()));
        }
        movimiento.setTipoMovimiento(tipoMovimiento);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = null;
        if(authentication != null)
            usuario = usuarioService.getUsuario(authentication.getName());

        movimiento.setUsuario(usuario!=null?usuario:new Usuario(1l,null,null,null,null,null));

        movimientoRepository.save(movimiento);
    }
}

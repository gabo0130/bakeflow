package com.bake.BakeFLowBackend.service.serviceImpl;

import com.bake.BakeFLowBackend.converter.ProductoConverter;
import com.bake.BakeFLowBackend.dto.ProductoDTO;
import com.bake.BakeFLowBackend.entity.Movimiento;
import com.bake.BakeFLowBackend.entity.Producto;
import com.bake.BakeFLowBackend.entity.TipoMovimiento;
import com.bake.BakeFLowBackend.entity.Usuario;
import com.bake.BakeFLowBackend.repository.ProductoRepository;
import com.bake.BakeFLowBackend.service.MovimientoService;
import com.bake.BakeFLowBackend.service.ProductoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    ProductoConverter productoConverter;

    @Autowired
    @Lazy
    MovimientoService movimientoService;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    UsuarioServiceImpl usuarioService;


    @Override
    @Transactional
    public Long registrarProducto(ProductoDTO productoDTO) {
        Producto producto = productoConverter.toProducto(productoDTO);

        // Guarda el producto primero
        producto = productoRepository.save(producto);
        registrarMovimiento(productoDTO, producto);
        return producto.getId();
    }

    @Override
    public List<ProductoDTO> obtenerProductos(String nombre, String descripcion, Long categoriaId, Double precio) {
        List<Producto> productos = productoRepository.buscarProductosConFiltros(nombre,descripcion, categoriaId, precio);
        return productoConverter.toProductoDTOs(productos);
    }

    @Override
    public ProductoDTO getProducto(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Producto no encontrado");
        });
        return productoConverter.toProductoDTO(producto);
    }


    public Boolean canBeMinus(Long id, Integer cantidad) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Producto no encontrado");
        });
         ProductoDTO dto = productoConverter.toProductoDTO(producto);
        return dto.getExistencias() >= cantidad;
    }

    @Override
    public Long actualizarProcuto(Long id, ProductoDTO productoDTO) {
        //Buscar el producto y actualizar los datos que se puedan actualizar
        Producto producto = productoRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Producto no encontrado");
        });
        producto = productoConverter.toProducto(productoDTO);
        return productoRepository.save(producto).getId()
                ;
        }

    @Override
    public void eliminarProducto(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Producto no encontrado");
        });
        producto.setHabilitado(false);
        productoRepository.save(producto);
    }

    private void registrarMovimiento(ProductoDTO productoDTO, Producto producto){
        Movimiento movimiento = new Movimiento();
        movimiento.setProducto(producto);
        movimiento.setCantidad(productoDTO.getCantidadInicial());

        if (productoDTO.getCostoUnitario()!= null) {
            movimiento.setCostoUnitario(productoDTO.getCostoUnitario());
            movimiento.setCostoTotal(productoDTO.getCostoUnitario() * productoDTO.getCantidadInicial());
        }

        if(productoDTO.getCostoTotal() != null){
            movimiento.setCostoTotal(productoDTO.getCostoTotal());
            movimiento.setCostoUnitario(productoDTO.getCostoTotal() / productoDTO.getCantidadInicial());
        }


        TipoMovimiento tipoMovimiento = new TipoMovimiento();
        tipoMovimiento.setId(1L);
        movimiento.setTipoMovimiento(tipoMovimiento);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = null;
        if(authentication != null)
            usuario = usuarioService.getUsuario(authentication.getName());

        movimiento.setUsuario(usuario!=null?usuario:new Usuario(1l,null,null,null,null,null));

        movimientoService.registrarMovimiento(movimiento);
    }
}

package com.bake.BakeFLowBackend.service.serviceImpl;

import com.bake.BakeFLowBackend.dto.BetweenDate;
import com.bake.BakeFLowBackend.dto.request.MovimientoRequest;
import com.bake.BakeFLowBackend.dto.request.MovimientosRequest;
import com.bake.BakeFLowBackend.dto.response.InformeVentasResponse;
import com.bake.BakeFLowBackend.dto.response.InformeVentasXCostosResponse;
import com.bake.BakeFLowBackend.dto.response.ProductoVendidoResponse;
import com.bake.BakeFLowBackend.entity.Movimiento;
import com.bake.BakeFLowBackend.entity.Producto;
import com.bake.BakeFLowBackend.entity.TipoMovimiento;
import com.bake.BakeFLowBackend.entity.Usuario;
import com.bake.BakeFLowBackend.repository.MovimientoRepository;
import com.bake.BakeFLowBackend.service.MovimientoService;
import com.bake.BakeFLowBackend.util.Operation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

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
    @Transactional
    public void registrarMovimiento(MovimientosRequest movimiento) {
        movimiento.getMovimientos().forEach(this::registrarMovimiento);
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


    @Override
    public List<Movimiento> informeMovimientosEntreFechas(Long productoId,Boolean esVenta,Date fechaInicio, Date fechaFin) {
        BetweenDate betweenDate = valideFechas(fechaInicio, fechaFin);
        return movimientoRepository.informeMovimientosEntreFechas(productoId,esVenta,betweenDate.getFechaInicio(), betweenDate.getFechaFin());
    }

    @Override
    public InformeVentasResponse informeVentasTotales(Long productoId, Date fechaInicio, Date fechaFin)  {
        BetweenDate betweenDate = valideFechas(fechaInicio, fechaFin);
        List<Movimiento> movimientos = movimientoRepository.informeMovimientosEntreFechas(productoId,true,betweenDate.getFechaInicio(),betweenDate.getFechaFin());

        Map<String, ProductoVendidoResponse> map = new HashMap<>();
        AtomicReference<Integer> totalProductosVendidos = new AtomicReference<>(0);
        AtomicReference<Double> totalVentas = new AtomicReference<>(0d);
        movimientos.forEach(movimiento -> {
            ProductoVendidoResponse producto = map.get(movimiento.getProducto().getNombre());
            if (producto == null){
                producto = new ProductoVendidoResponse();
                producto.setCantidad(movimiento.getCantidad());
                producto.setTotalVenta(movimiento.getCostoTotal());
                producto.setPrecioVentaPromedio(movimiento.getCostoTotal() / movimiento.getCantidad());
                totalVentas.updateAndGet(v -> v + movimiento.getCostoTotal());
                totalProductosVendidos.updateAndGet(v -> v + movimiento.getCantidad());
                map.put(movimiento.getProducto().getNombre(),producto);
            }else{
                producto.setCantidad(producto.getCantidad() + movimiento.getCantidad());
                producto.setTotalVenta(producto.getTotalVenta() + movimiento.getCostoTotal());
                producto.setPrecioVentaPromedio(producto.getTotalVenta() / producto.getCantidad());
                totalVentas.updateAndGet(v -> v + movimiento.getCostoTotal());
                totalProductosVendidos.updateAndGet(v -> v + movimiento.getCantidad());
            }
        });
        InformeVentasResponse response = new InformeVentasResponse(map,totalProductosVendidos.get(),totalVentas.get());
        return response;
    }

    @Override
    public InformeVentasXCostosResponse informeVentasTotalesXcosto(Long productoId) {
        //Generar informe de ventas contra costos de producción.

        BetweenDate betweenDate = valideFechas(null, null);
        List<Movimiento> movimientos = movimientoRepository.informeMovimientosEntreFechas(productoId,null,betweenDate.getFechaInicio(),betweenDate.getFechaFin());
        Map<String, ProductoVendidoResponse> map = new HashMap<>();
        AtomicReference<Double> totalVentas  = new AtomicReference<>(0d);
        AtomicReference<Double> totalCostos  = new AtomicReference<>(0d);
        Double totalGanancias   = 0d;
        movimientos.forEach(movimiento -> {
            ProductoVendidoResponse producto = map.get(movimiento.getProducto().getNombre());
            if (producto == null){
                producto = new ProductoVendidoResponse();
                if(movimiento.esVenta()){
                    producto.setCantidad(movimiento.getCantidad());
                    producto.setTotalVenta(movimiento.getCostoTotal());
                    producto.setPrecioVentaPromedio(movimiento.getCostoTotal() / movimiento.getCantidad());
                    totalVentas.updateAndGet(v -> v + movimiento.getCostoTotal());
                }else if(movimiento.esPerdida()){
                    producto.setCantidad(movimiento.getCantidad());
                    producto.setTotalVenta(0D);
                    producto.setTotalCosto(0D);
                }else if(movimiento.esCompra()){
                    producto.setCantidad(0);
                    producto.setTotalVenta(0D);
                    producto.setCostoPromedio(movimiento.getCostoTotal() / movimiento.getCantidad());
                    producto.setTotalCosto(movimiento.getCostoTotal());
                    totalCostos.updateAndGet(v -> v + movimiento.getCostoTotal());
                }
                map.put(movimiento.getProducto().getNombre(),producto);
            }else{
                if(movimiento.esVenta()){
                    producto.setCantidad(producto.getCantidad() + movimiento.getCantidad());
                    producto.setTotalVenta(producto.getTotalVenta() + movimiento.getCostoTotal());
                    producto.setPrecioVentaPromedio(producto.getTotalVenta() / producto.getCantidad());
                    totalVentas.updateAndGet(v -> v + movimiento.getCostoTotal());
                }else if(movimiento.esPerdida()){
                    producto.setCantidad(producto.getCantidad() + movimiento.getCantidad());
                }else if(movimiento.esCompra()){
                    producto.setTotalCosto(producto.getTotalCosto() + movimiento.getCostoTotal());
                    totalCostos.updateAndGet(v -> v + movimiento.getCostoTotal());
                }
            }
        });
        InformeVentasXCostosResponse response = new InformeVentasXCostosResponse(map,totalVentas.get(),totalCostos.get(),totalGanancias);
        return response;
    }

    private BetweenDate valideFechas(Date fechaInicio, Date fechaFin) {
        BetweenDate betweenDate = new BetweenDate();
        if (fechaInicio == null)
            fechaInicio = new Date(0);
        if (fechaFin == null)
            fechaFin = new Date();

        if (fechaInicio.after(fechaFin))
            throw new IllegalArgumentException("La fecha de inicio no puede ser mayor a la fecha de fin");

        fechaInicio.setHours(0);
        fechaInicio.setMinutes(0);
        fechaInicio.setSeconds(0);
        fechaFin.setHours(23);
        fechaFin.setMinutes(59);
        fechaFin.setSeconds(59);
        betweenDate.setFechaInicio(fechaInicio);
        betweenDate.setFechaFin(fechaFin);
        return betweenDate;
    }
}

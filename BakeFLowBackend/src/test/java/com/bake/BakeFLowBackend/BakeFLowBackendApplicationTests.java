package com.bake.BakeFLowBackend;

import com.bake.BakeFLowBackend.controller.MovimientoController;
import com.bake.BakeFLowBackend.controller.ProductoController;
import com.bake.BakeFLowBackend.controller.UsuarioController;
import com.bake.BakeFLowBackend.converter.ProductoConverter;
import com.bake.BakeFLowBackend.dto.ProductoDTO;
import com.bake.BakeFLowBackend.dto.UsuarioDTO;
import com.bake.BakeFLowBackend.dto.request.MovimientoRequest;
import com.bake.BakeFLowBackend.dto.response.BaseResponse;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BakeFLowBackendApplicationTests {


	@Autowired
	UsuarioController usuarioController;

	@Autowired
	ProductoController productoController;

	@Autowired
	ProductoConverter productoConverter;

	@Autowired
	MovimientoController movimientoController;

	@Test
	void contextLoads() {
	}

	@Test
	void crearUsuario() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNombre("Nombre");
		usuarioDTO.setDocumento("Documento");
		usuarioDTO.setUsuario("Usuario");
		usuarioDTO.setPassword("Password");
		usuarioDTO.setTipoDocumentoId(1L);
		usuarioController.registrarUsuario(usuarioDTO);
	}

	@Test
	void obtenerUsuarios() {
		usuarioController.obtenerUsuarios();

	}

	@Test
	void registrarProducto() {
		ProductoDTO productoDTO = new ProductoDTO();
		productoDTO.setNombre("torta");
		productoDTO.setDescripcion("Torta de chocolate");
		productoDTO.setPrecio(25000.0);
		productoDTO.setCantidadInicial(1);
		productoDTO.setCategoriaId(3L);
		productoDTO.setCostoUnitario(18000.0);
		productoController.registrarProducto(productoDTO);
	}

	@Test
	void obtenerProductos() {
		ResponseEntity<BaseResponse> x =  productoController.obtenerProductos("pepsi",null,null,null);
System.out.println(x.getBody().toString());

	}

	@Test
	void probarCreacionProductoYMovimientos() {

		Integer cantidad = 10;
		Double precioSugerido = 8000.0;
		Double costoUnitario = 4000.50;
		ProductoDTO productoDTO = crearProducto("pan de centeno", "El pan de centeno es un tipo de pan elaborado con harina de centeno, la panificación muestra una miga más oscura que el pan de trigo", precioSugerido, cantidad, 2L, costoUnitario);
        String nombre = productoDTO.getNombre();
		verificarExistencias(nombre, cantidad);

		agregarExistencias(productoDTO.getId(), cantidad*2, costoUnitario);

		verificarExistencias(nombre, cantidad*3);

		venderProductos(productoDTO.getId(), cantidad+2, precioSugerido);

		verificarExistencias(nombre, cantidad*2-2);
	}

	private ProductoDTO crearProducto(String nombre, String descripcion, Double precio, int cantidadInicial, Long categoriaId, Double costoUnitario) {
		ProductoDTO productoDTO = new ProductoDTO();
		productoDTO.setNombre(nombre);
		productoDTO.setDescripcion(descripcion);
		productoDTO.setPrecio(precio);
		productoDTO.setCantidadInicial(cantidadInicial);
		productoDTO.setCategoriaId(categoriaId);
		productoDTO.setCostoUnitario(costoUnitario);

		ResponseEntity<BaseResponse> respuesta = productoController.registrarProducto(productoDTO);
		assertEquals(200, respuesta.getStatusCodeValue(), "Error al crear producto");

		ResponseEntity<BaseResponse> respuestaObtener = productoController.obtenerProductos(nombre, null, null, null);
		List<ProductoDTO> productos = (List<ProductoDTO>) respuestaObtener.getBody().getData();
		assertEquals(1, productos.size(), "Error al obtener productos");

		return productos.get(0);
	}

	private void verificarExistencias(String nombre, int existenciasEsperadas) {
		ResponseEntity<BaseResponse> respuestaObtener = productoController.obtenerProductos(nombre, null, null, null);
		List<ProductoDTO> productos = (List<ProductoDTO>) respuestaObtener.getBody().getData();
		assertEquals(1, productos.size(), "Error al obtener productos");
		ProductoDTO productoDTO = productos.get(0);
		System.out.println("Producto: " + productoDTO.getNombre() + " existencias " + productoDTO.getExistencias());
		assertEquals(existenciasEsperadas, productoDTO.getExistencias(), "Error en verificación de existencias");
	}

	private void agregarExistencias(Long productoId, int cantidad, Double costoUnitario) {
		System.out.println("******************** Se agregan más existencias (" + cantidad + ") ******************************");
		MovimientoRequest movimientoRequest = new MovimientoRequest();
		movimientoRequest.setCantidad(cantidad);
		movimientoRequest.setCostoUnitario(costoUnitario);
		movimientoRequest.setProductoId(productoId);
		movimientoRequest.setTipoMovimientoId(1L);

		movimientoController.registrarMovimiento(movimientoRequest, null);
	}

	private void venderProductos(Long productoId, int cantidad, Double costoUnitario) {
		System.out.println("******************** Se venden productos (" + cantidad + ") ******************************");
		MovimientoRequest movimientoRequest = new MovimientoRequest();
		movimientoRequest.setCantidad(cantidad);
		movimientoRequest.setCostoUnitario(costoUnitario);
		movimientoRequest.setProductoId(productoId);
		movimientoRequest.setTipoMovimientoId(2L);

		movimientoController.registrarMovimiento(movimientoRequest, null);
	}


}


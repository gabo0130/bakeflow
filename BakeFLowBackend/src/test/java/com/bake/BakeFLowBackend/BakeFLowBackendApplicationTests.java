package com.bake.BakeFLowBackend;

import com.bake.BakeFLowBackend.controller.MovimientoController;
import com.bake.BakeFLowBackend.controller.ProductoController;
import com.bake.BakeFLowBackend.controller.UsuarioController;
import com.bake.BakeFLowBackend.converter.ProductoConverter;
import com.bake.BakeFLowBackend.dto.CategoriaDTO;
import com.bake.BakeFLowBackend.dto.ProductoDTO;
import com.bake.BakeFLowBackend.dto.UsuarioDTO;
import com.bake.BakeFLowBackend.dto.request.MovimientoRequest;
import com.bake.BakeFLowBackend.dto.response.BaseResponse;
import com.bake.BakeFLowBackend.entity.Movimiento;
import com.bake.BakeFLowBackend.entity.Producto;
import com.bake.BakeFLowBackend.entity.TipoMovimiento;
import com.bake.BakeFLowBackend.util.Operation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.beans.PropertyEditor;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		productoDTO.setPrecio(25000);
		productoDTO.setCantidadInicial(1);
		productoDTO.setCategoriaId(3L);
		productoDTO.setCostoUnitario(18000);
		productoController.registrarProducto(productoDTO);
	}

	@Test
	void obtenerProductos() {
		ResponseEntity<BaseResponse> x =  productoController.obtenerProductos("pepsi",null,null,null);
System.out.println(x.getBody().toString());

	}

	@Test
	void creacionDeProductoYMovimientos() {
		/**ProductoDTO productoDTO = new ProductoDTO();
		productoDTO.setNombre("pepsi");
		productoDTO.setDescripcion("200ml");
		productoDTO.setPrecio(25000);
		productoDTO.setCantidadInicial(20);
		productoDTO.setCategoriaId(1L);
		productoDTO.setCostoUnitario(18000);
		ResponseEntity<BaseResponse> x = productoController.registrarProducto(productoDTO);
		System.out.println(x.getBody().toString());*/
		ResponseEntity<BaseResponse> y =  productoController.obtenerProductos("pepsi",null,null,null);
		System.out.println(y.getBody().toString());

		List<ProductoDTO> productos = (List<ProductoDTO>) y.getBody().getData();
		ProductoDTO producto = productos.get(0);
		MovimientoRequest movimientoRequest = new MovimientoRequest();
		movimientoRequest.setCantidad(10);
		movimientoRequest.setCostoUnitario(18000);

		movimientoRequest.setProductoId(producto.getId());
		movimientoRequest.setTipoMovimientoId(1L);

		movimientoController.registrarMovimiento(movimientoRequest, null);
		ResponseEntity<BaseResponse> z =  productoController.obtenerProductos("pepsi",null,null,null);
		System.out.println(z.getBody().toString());
	}

}

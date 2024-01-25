package com.bake.BakeFLowBackend;

import com.bake.BakeFLowBackend.controller.ProductoController;
import com.bake.BakeFLowBackend.controller.UsuarioController;
import com.bake.BakeFLowBackend.converter.ProductoConverter;
import com.bake.BakeFLowBackend.dto.CategoriaDTO;
import com.bake.BakeFLowBackend.dto.ProductoDTO;
import com.bake.BakeFLowBackend.dto.UsuarioDTO;
import com.bake.BakeFLowBackend.dto.response.BaseResponse;
import com.bake.BakeFLowBackend.entity.Movimiento;
import com.bake.BakeFLowBackend.entity.Producto;
import com.bake.BakeFLowBackend.entity.TipoMovimiento;
import com.bake.BakeFLowBackend.util.Operation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class BakeFLowBackendApplicationTests {


	@Autowired
	UsuarioController usuarioController;

	@Autowired
	ProductoController productoController;

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
		productoDTO.setDescripcion("toratatatat");
		productoDTO.setPrecio(25000);
		productoDTO.setCantidadInicial(1);
		productoDTO.setCategoriaId(3L);
		productoDTO.setCostoUnitario(18000);
		productoController.registrarProducto(productoDTO);
	}

	@Test
	void obtenerProductos() {
		productoController.obtenerProductos(null,null,null,null);
	}

}

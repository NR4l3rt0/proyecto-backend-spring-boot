package com.jubiter.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jubiter.modelo.Producto;


/**
 * Se sigue un estilo BDD para desarrollar los tests usando AssertJ y JUnit5.
 * Se ejemplifica la forma de testear un repositorio.
 * La anotación @DataJpaTest inicia el programa y realiza la inyección en @Autowired
 * @author nr_alberto
 *
 */
@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
class ProductoRepositoryTest {
	
		@Autowired
		private ProductoRepository productoUnderTest;
		
		/**
		 * Se asegura que no existe ningún registro tras cada método (limpia el estado)
		 */
		@AfterAll
		void tearDown() {
			productoUnderTest.deleteAll();
		}
		
		
	/**
	 * Comprueba el cas de que no sea vacío, un valor objeto BigDecimal y un String
	 */
	@Test
	//@Disabled
	void itshouldGetAProductByIdIntNotInteger() {
	
		// given 
		int idProducto = 1;
		Producto producto = new Producto("pavo", "cocido",
					    			"Frial", LocalDate.parse("2021-08-01"),
					    			100, BigDecimal.valueOf(10.95));
		
		productoUnderTest.save(producto);
		
		// when
		Producto existeProducto = productoUnderTest.findById(idProducto);

		// then
		assertThat(existeProducto.getNombre()).isNotBlank();
		assertThat(existeProducto.getPrecio()).isEqualTo(BigDecimal.valueOf(10.95));
		assertThat(existeProducto.getProveedor()).isEqualTo("Frial");
		
								 
	}
	
	/**
	 * Haciendo uso de la clase Optional, se hace una comprobación de tipo 
	 * y se comprueba que no existe.
	 */
	@Test
	void itshouldNotGetAnyProductById() {
	
		// given 
		int idProducto = 1;

		// Devolverá un Optional.empty
		// when 
		Optional<Producto> existeProducto = Optional.ofNullable(productoUnderTest.findById(idProducto));

		// then	
		assertThat(existeProducto).isNotInstanceOf(Producto.class);
		assertThat(existeProducto.isPresent()).isFalse();

		
								 
	}

}

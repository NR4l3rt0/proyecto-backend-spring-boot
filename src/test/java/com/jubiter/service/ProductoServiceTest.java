package com.jubiter.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jubiter.modelo.Producto;
import com.jubiter.repository.ProductoRepository;

/**
 * En esta clase se trabajan con Mocks para poder agilizar
 * el proceso de testing, separar responsabilidades y minimizar
 * los recursos que se necesitan.
 * 
 * PD: no he podido usarlo bien porque me apunta a null la instancia del
 * repo; y en consecuencia tampoco que podido realizar test de coverage aquí.
 * 
 * Otro tipo de tests que se ha pensado es el testing local como test 
 * de sistema usando Docker y puertos locales.
 * 
 * @author nr_alberto
 *
 */
@ExtendWith(MockitoExtension.class)   // Elimina la necesidad de usar AutoCloseable
class ProductoServiceTest {

	/**
	 * Como se sabe que el funciona, en vez de @Autowired, se
	 * puede trabajar con Mocks (Mockito en este caso)
	 */
	@Mock
	private ProductoRepository productoRepository;
	//private AutoCloseable autoCloseable;
	private ProductoService productoUnderTest;
	
	@BeforeEach
	void setUp() {
		// Inicaliza todos los mocks de la clase
		//autoCloseable = MockitoAnnotations.openMocks(this);
		productoUnderTest = new ProductoService();
		
	}
	
	/*@AfterEach
	void tearDown() throws Exception{
		// cierra los recursos tras los tests
		//try {
			autoCloseable.close();
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}*/
	
	
	@Test
	@Disabled				
	// No he conseguido que funcione al no estar el repositorio inyectado en constructor
	void canGetAllProductos() {
		// when
		productoUnderTest.getAllProductos();
		
		
		// then
		// verifica que el método ha invocado a findAll()
		verify(productoRepository).findAll();

	}
	
	
	@Test
	@Disabled
	void canAddOneProducto() {
		// given
		Producto producto = new Producto("pavo", "cocido",
    			"Frial", LocalDate.parse("2021-08-01"),
    			100, BigDecimal.valueOf(10.95));
		
		// when
		productoUnderTest.addProducto(producto);
		
		// then
		// en las dos sentencias se consigue chequear que el repositorio
		// ha guardado el producto que le hemos pasado y lo captura
		ArgumentCaptor<Producto> productoArgumentCapture =
				ArgumentCaptor.forClass(Producto.class);
		
		verify(productoRepository).save(productoArgumentCapture.capture());
		
		// toma el valor
		Producto productoCapturado = productoArgumentCapture.getValue();
		// lo chequea
		assertThat(productoCapturado).isEqualTo(producto);
	}

}

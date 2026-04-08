package com.example.demo;

import com.example.demo.controller.helloC;
import com.example.demo.service.helloS;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private helloS helloService;

	@Autowired
	private helloC helloController;

	@Test
	void contextLoads() {
		// Verifica que el contexto se carga correctamente
		assert helloService != null;
		assert helloController != null;
	}

	@Test
	void testHelloServiceMessage() {
		// Prueba unitaria del servicio
		String message = helloService.getHelloMessage();
		assertEquals("prueaaaada", message);
	}

	@Test
	void testHelloEndpoint() throws Exception {
		// Prueba de integración del endpoint REST
		mockMvc.perform(get("/hello"))
				.andExpect(status().isOk())
				.andExpect(content().string("prueaaaada"));
	}

	@Test
	void testHelloControllerLogic() {
		// Prueba del controlador de forma aislada
		String result = helloController.hello();
		assertEquals("prueaaaada", result);
	}

}

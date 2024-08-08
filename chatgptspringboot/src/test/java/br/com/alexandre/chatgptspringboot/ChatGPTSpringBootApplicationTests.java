package br.com.alexandre.chatgptspringboot;

import br.com.alexandre.chatgptspringboot.configappl.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {ChatGPTSpringBootApplication.class, TestConfig.class})
class ChatGPTSpringBootApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testMainMethod() {
		// Redireciona a saída padrão para capturar mensagens de erro
		try {
			// Chama o método main para verificar se ele executa sem erros
			ChatGPTSpringBootApplication.main(new String[] {});
		} catch (Exception e) {
			// Caso ocorra uma exceção, o teste falhará
			e.printStackTrace();
			throw new RuntimeException("O método main falhou ao executar", e);
		}
	}
}

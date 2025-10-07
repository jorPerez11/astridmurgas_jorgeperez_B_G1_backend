package pruebaFinal.JorgePerez_AstridMurgas_B_G1;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JorgePerezAstridMurgasBG1Application {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry ->
					System.setProperty(entry.getKey(), entry.getValue())
				);

		SpringApplication.run(JorgePerezAstridMurgasBG1Application.class, args);
	}

}

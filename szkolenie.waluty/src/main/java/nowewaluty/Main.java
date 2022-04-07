package nowewaluty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nowewaluty.exceptions.DaoSessionException;

@SpringBootApplication
public class Main {
	public static void main(String[] args) throws DaoSessionException {

		SpringApplication.run(Main.class, args);

	}

}

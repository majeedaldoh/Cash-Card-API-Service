package example.cashcard;
import java.lang.*;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CashCardApplication {

	private static final Logger log = LoggerFactory.getLogger(CashCardApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CashCardApplication.class, args);
	}

	@Bean
	public CommandLineRunner query(CashCardRepository repository){
		return (args) -> {
			repository.save(new CashCard( 100.0));
			repository.save(new CashCard( 50.0));

			log.info("CashCards Found by findAll() are:");
			repository.findAll().forEach(cashCard ->{
				log.info(cashCard.toString());
			});
			Optional<CashCard> cashCard1 = repository.findById(1L);
			log.info(cashCard1.toString());
		};
	}
}
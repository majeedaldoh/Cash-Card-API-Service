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
			CashCard cashCard1 = new CashCard();
			CashCard cashCard2 = new CashCard();
			cashCard1.setAmount(100.0);
			cashCard2.setAmount(200.0);
			repository.save(cashCard1);
			repository.save(cashCard2);

			log.info("CashCards Found by findAll() are:");
			repository.findAll().forEach(cashCard ->{
				log.info(cashCard.toString());
			});
			/*Optional<CashCard> cashCard = repository.findById(1L);
			log.info(cashCard1.toString());*/
		};
	}
}
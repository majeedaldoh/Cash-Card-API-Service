package example.cashcard;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CashCardApplicationTests {
	@Autowired
	TestRestTemplate restTemplate;
//	CashCard cashCard = new CashCard(100, 0.0);
	@Test
	void shouldReturnACashCardWhenDataIsSaved() {
		ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/99", String.class);
		// checking if we hit the right endpoint
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		// now we need to check if our success request has values with same expected attributes? if yes? are they correct?
		DocumentContext documentContext = JsonPath.parse(response.getBody());
		Number id = documentContext.read("$.id");
		Double amount = documentContext.read("$.amount");

		assertThat(id).isEqualTo(99);
		assertThat(amount).isEqualTo(123.45);
	}

	@Test
	void shouldNotReturnACashCardWithUnknown(){
		ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/100", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	}

//	@Test
//	void shouldCreateNewCashCard(){
//		CashCard cashCard = new CashCard( 100.0);
//		ResponseEntity<Void> createResponse = restTemplate.postForEntity("/cashcards", cashCard, Void.class);
//
//		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//	}
}
package example.cashcard;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;


import static org.assertj.core.api.Assertions.assertThat;


@JsonTest
public class CashCardJsonTest {

    @Autowired
    private JacksonTester<CashCard> json;
    CashCardRepository repository;
    @Test
    void cashCardSerializationTest() throws IOException{
        CashCard cashCard = new CashCard();
        cashCard.setAmount(123.45);

        repository.save(cashCard);
//        CashCard testCashCardEntity =
        assertThat(json.write(cashCard)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
//        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id").isEqualTo(99);
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount").isEqualTo(123.45);
    }
}
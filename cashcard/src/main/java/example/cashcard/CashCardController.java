package example.cashcard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;


@RestController
@RequestMapping("/cashcards")
public class CashCardController {

    private final CashCardRepository cashCardRepository;

    private CashCardController(CashCardRepository cashCardRepository){
        this.cashCardRepository = cashCardRepository;
    }
    @GetMapping("/{requestedId}")
    private ResponseEntity<CashCard> findById(@PathVariable Long requestedId){
        Optional<CashCard> optionalCashCard = cashCardRepository.findById(requestedId);
        // calling the cashcard table and checking that we have a cashcard with the requested id
        if(optionalCashCard.isPresent()) {
            // code to call repository to call the cashcard with requested id and return the row.
            return ResponseEntity.ok(optionalCashCard.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
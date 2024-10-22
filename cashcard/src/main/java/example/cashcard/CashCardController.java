package example.cashcard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;
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

    @PostMapping("")
    private ResponseEntity<Void> createCashCard(@RequestBody CashCard newCashCard, UriComponentsBuilder ucb) {
        CashCard savedCashCard = cashCardRepository.save(newCashCard);
        //Long objectID = cashCard.getId();
        URI locationOfNewCardCash = ucb
                .path("/cashcards/{objectID}")
                .buildAndExpand(savedCashCard.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewCardCash).build();
    }
}
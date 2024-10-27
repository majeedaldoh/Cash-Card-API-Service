package example.cashcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cashcards")
public class CashCardController {

    @Autowired
    private final CashCardRepository cashCardRepository;
    CashCardService cardService;

    private CashCardController(CashCardRepository cashCardRepository) {
        this.cashCardRepository = cashCardRepository;
    }

    @GetMapping("")
    private void greet(){
        System.out.println("Greetings from CashCardController");
    }
    @GetMapping("/{requestedId}")
    private CashCard findById(@PathVariable Long requestedId) {
        return cardService.findById(requestedId);
    }

    @GetMapping("/all")
    private List<CashCard> findAll() {
        return cardService.findAll();
    }


    @PostMapping("/add")
    private ResponseEntity<Void> createCashCard(@RequestParam double amount, UriComponentsBuilder ucb) {


        CashCard newCashCard = new CashCard();
        newCashCard.setAmount(amount);
        return cardService.save(newCashCard, ucb);
    }
}
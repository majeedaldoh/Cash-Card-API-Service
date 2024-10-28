package example.cashcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

// ToDo complete the CashCard CRUD Ops
@RestController
@RequestMapping("/cashcards")
public class CashCardController {

    @Autowired
    CashCardService cardService;

    @GetMapping("/{requestedId}")
    private CashCard findById(@PathVariable Long requestedId) {
        return cardService.findById(requestedId);
    }
    @GetMapping("/all")
    private List<CashCard> findAll() {
        return cardService.findAll();
    }
    @PostMapping("/add")
    private ResponseEntity<Void> createCashCard(@RequestBody CashCard cashCard, UriComponentsBuilder ucb) {
        cashCard.setAmount(cashCard.getAmount());
        return cardService.save(cashCard, ucb);
    }
    @PutMapping("/update/{requestedId}")
    private ResponseEntity<Void> update(@PathVariable Long requestedId, @RequestBody CashCard cashCard) {
        cashCard.setAmount(cashCard.getAmount());
        return cardService.update(requestedId, cashCard);
    }
    @DeleteMapping("/delete/{requestedId}")
    private ResponseEntity<Void> deleteById(@PathVariable Long requestedId) {
        return cardService.delete(requestedId);
    }
}
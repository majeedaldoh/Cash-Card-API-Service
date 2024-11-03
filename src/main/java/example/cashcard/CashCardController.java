package example.cashcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

// ToDo: Check when and when not to add the constroctor?
@RestController
@RequestMapping("/cashcards")
public class CashCardController {

    @Autowired
    CashCardService cardService;

    @PostMapping("/add")
    private ResponseEntity<Void> save(@RequestBody CashCard cashCard, UriComponentsBuilder ucb) {
        cashCard.setAmount(cashCard.getAmount());
        return cardService.save(cashCard, ucb);
    }
    @GetMapping("/{requestedId}")
    private ResponseEntity<CashCard> find(@PathVariable Long requestedId) {
        return cardService.find(requestedId);
    }
    @GetMapping("/all")
    private ResponseEntity<List<CashCard>> findAll() {
        return cardService.findAll();
    }
    @PutMapping("/update/{requestedId}")
    private ResponseEntity<Void> update(@PathVariable Long requestedId, @RequestBody CashCard cashCard) {
        cashCard.setAmount(cashCard.getAmount());
        return cardService.update(requestedId, cashCard);
    }
    @DeleteMapping("/delete/{requestedId}")
    private ResponseEntity<Void> delete(@PathVariable Long requestedId) {
        return cardService.delete(requestedId);
    }
}
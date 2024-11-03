package example.cashcard;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CashCardService {
    @Autowired
    private CashCardRepository cashCardRepository;

    public ResponseEntity<Void> save(CashCard cashCard, UriComponentsBuilder ucb) {
        CashCard savedCashCard = cashCardRepository.save(cashCard);

        URI locationOfNewCashCard = ucb
                .path("cashcards/{ObjectId}")
                .buildAndExpand(savedCashCard.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    }

    public ResponseEntity<CashCard> find(Long id) {
        Optional<CashCard> cashCard = cashCardRepository.findById(id);
        return cashCard.map(card -> ResponseEntity.ok().body(card)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<CashCard>> findAll() {
        return ResponseEntity.ok().body(cashCardRepository.findAll());
    }
    public ResponseEntity<Void> update(Long id, CashCard cashCard) {
        Optional<CashCard> existingCashCard = cashCardRepository.findById(id);
        if (existingCashCard.isPresent()) {
            CashCard cashCardToUpdate = existingCashCard.get();
            cashCardToUpdate.setAmount(cashCard.getAmount());
            cashCardRepository.save(cashCardToUpdate);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<Void> delete(Long id) {
        if (cashCardRepository.existsById(id)) {
            cashCardRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

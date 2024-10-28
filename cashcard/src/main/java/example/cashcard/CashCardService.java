package example.cashcard;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CashCardService {
    @Autowired
    private CashCardRepository cashCardRepository;

    public CashCard findById(Long id) {
        Optional<CashCard> cashCard = cashCardRepository.findById(id);
        if (cashCard.isPresent()) {
            return cashCard.get();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found"
            );
        }
    }

    public List<CashCard> findAll() {
        return cashCardRepository.findAll();
    }

    public ResponseEntity<Void> save(CashCard cashCard, UriComponentsBuilder ucb) {
        CashCard savedCashCard =  cashCardRepository.save(cashCard);

        URI locationOfNewCashCard = ucb
                .path("cashcards/{ObjectId}")
                .buildAndExpand(savedCashCard.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    }

    public ResponseEntity<Void> update(Long id, CashCard cashCard) {
        Optional<CashCard> existingCashCard= cashCardRepository.findById(id);
        if (existingCashCard.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }else {
            CashCard cashCardToUpdate = existingCashCard.get();
            cashCardToUpdate.setAmount(cashCard.getAmount());
            cashCardRepository.save(cashCardToUpdate);
            return ResponseEntity.noContent().build();
        }
    }
    public ResponseEntity<Void> delete(Long id){
        if(cashCardRepository.existsById(id)){
            cashCardRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        }
    }
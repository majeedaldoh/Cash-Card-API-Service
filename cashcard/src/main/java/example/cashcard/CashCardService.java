package example.cashcard;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;


public class CashCardService {
    private CashCardRepository cashCardRepository;

    public List<CashCard> findAll() {
        return cashCardRepository.findAll();
    }
    public CashCard findById(Long id) {
        Optional<CashCard> cashCard = cashCardRepository.findById(id);
        if (cashCard.isPresent()) {return cashCard.get();} else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found"
            );
        }
    }
    public ResponseEntity<Void> save(CashCard cashCard, UriComponentsBuilder ucb) {
        CashCard savedCashCard =  cashCardRepository.save(cashCard);

        URI locationOfNewCashCard = ucb
                .path("cashcards/{ObjectId}")
                .buildAndExpand(savedCashCard.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    }

//    public CashCard update(Long id, CashCard cashCard) {
//        Optional<CashCard> cashCardOptional= cashCardRepository.findById(id);
//        if (cashCardOptional.isEmpty()){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found");
//        }else {
//            CashCard cashCardToUpdate = cashCardOptional.get();
//            return cashCardRepository.save(cashCardToUpdate);
//        }
//    }
//    public void delete(Long id){
//        cashCardRepository.deleteById(id);
//        }
    }
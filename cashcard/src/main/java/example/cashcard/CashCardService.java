package example.cashcard;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

ResponseStatusException

@Service
public class CashCardService {
    @Autowired
    private CashCardRepository cashCardRepository;

    public List<CashCard> findAll() {
        return cashCardRepository.findAll();
    }
    public CashCard findById(int id) {
        Optional<CashCard> cashCard = cashCardRepository.findById(id);
        if (cashCard.isPresent()) {return cashCard.get();} else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found"
            );
        }
    }

    public CashCard save(CashCard cashCard) {
        return cashCardRepository.save(cashCard);
    }
    public CashCard update(Long id, CashCard cashCard) {
        Optional<CashCard> cashCardOptional= cashCardRepository.findById(id);
        if (cashCardOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found");
        }else {
            CashCard cashCardToUpdate = cashCardOptional.get();
            return cashCardRepository.save(cashCardToUpdate);
        }
        public void delete(Long id){
            cashCardRepository.deleteById(id);
        }

    }
}

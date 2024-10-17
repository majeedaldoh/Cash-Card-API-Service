package example.cashcard;

import org.springframework.data.repository.CrudRepository;

// CREATE the database
// connect to the database of the actual
public interface CashCardRepository extends CrudRepository<CashCard, Long>{
}

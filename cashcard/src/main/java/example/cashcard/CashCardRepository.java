package example.cashcard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

// TODO add a custom queries.
@Component
public interface CashCardRepository extends JpaRepository<CashCard, Long> {

}

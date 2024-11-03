package example.cashcard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// TODO add a custom queries.
@Repository
public interface CashCardRepository extends JpaRepository<CashCard, Long> {

}

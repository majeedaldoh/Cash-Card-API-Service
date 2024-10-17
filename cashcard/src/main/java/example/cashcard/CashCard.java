package example.cashcard;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class CashCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double amount;

    // protected CashCard(){}

    public CashCard(double amount) {
        this.amount = amount;
    }

    public String toString() {
        return String.format("CashCard[id=%d, amount=%f]", id, amount);
    }
    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }
}

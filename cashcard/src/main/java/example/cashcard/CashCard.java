package example.cashcard;
import jakarta.persistence.*;

@Entity
@Table(name = "cashcard")
public class CashCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "amount")
    private double amount;

    protected CashCard(){}

    /*public CashCard(double amount) {
        this.amount = amount;
    }*/

    public String toString() {
        return String.format("CashCard[id=%d, amount=%f]", id, amount);
    }
    public Long getId() {
        return id;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
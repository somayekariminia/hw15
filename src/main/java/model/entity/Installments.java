package model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Installments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    double amount;
    @Temporal(value = TemporalType.DATE)
    Date date;
    boolean isPaid;
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    StudentLoan studentLoan;
    @Override
    public String toString() {
        return
                " id: " + id + " amount: " + amount + "  date: " + date + " isPaid: " + isPaid +"\n";
    }
}

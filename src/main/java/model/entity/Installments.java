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
@EqualsAndHashCode()
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Installments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int numberInstallments;
    double amount;
    @Temporal(value = TemporalType.DATE)
    Date date;
    boolean isPaid;
    @ManyToOne
    StudentLoan studentLoan;
}

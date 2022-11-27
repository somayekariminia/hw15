package model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AllocatedLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    @ManyToOne
    Student student;
    @ManyToOne
    Loan loan;
    @Temporal(value = TemporalType.DATE)
    Date receiveDate;
    @OneToMany
    List<AllocatedLoan> allocatedLoanList;
    @OneToOne
    CreditCard creditCard;
}

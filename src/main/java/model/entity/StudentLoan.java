package model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@FieldDefaults(level = AccessLevel.PRIVATE)
@IdClass(StudentLoanId.class)
public class StudentLoan {
    @Id
    @ManyToOne
    Student student;
    @Id
    @ManyToOne
    Loan loan;
    @Temporal(value = TemporalType.TIMESTAMP)
    Date receiveDate;
    @OneToMany(mappedBy = "studentLoan", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<Installments> installments = new HashSet<>();
    @OneToOne
    CreditCard creditCard;
    String lease;
    int numberIsPaid;
    int numberIsNotPaid;
}

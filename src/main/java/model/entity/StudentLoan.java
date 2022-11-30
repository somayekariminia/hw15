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
@IdClass(StudentLoanId.class)
public class StudentLoan {
    @Id
    @ManyToOne
    Student student;
    @Id
    @ManyToOne
    Loan loan;
    @Temporal(value = TemporalType.TIME)
    Date receiveDate;
    @OneToMany
    List<Installments> installments;
    @OneToOne
    CreditCard creditCard;
    String lease;
}

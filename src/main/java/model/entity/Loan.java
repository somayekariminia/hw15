package model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import model.enumes.TypePayment;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@FieldDefaults(level = AccessLevel.PRIVATE)
@Inheritance(strategy = InheritanceType.JOINED)
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    double amount;
    @Enumerated(value=EnumType.STRING)
    TypePayment typePayment;
    @OneToMany(mappedBy = "loan")
    List<StudentLoan> loanList;
}

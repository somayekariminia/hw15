package model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import model.enumes.Degree;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student extends Person {
    String numberStudent;
    @ManyToOne(cascade = CascadeType.REFRESH)
    University university;
    Integer enterYear;
    @Enumerated
    Degree degree;
    @OneToOne(cascade = CascadeType.PERSIST)
    InfoAccount infoAccount;
    boolean dorm;
    @OneToOne(cascade = CascadeType.PERSIST)
    Address address;
    @OneToMany
    List<StudentLoan> studentLoanList=new ArrayList<>();
    @OneToOne(cascade = CascadeType.PERSIST)
    Spouse spouse;
    @OneToOne(cascade = CascadeType.PERSIST)
    CreditCard creditCard;
}

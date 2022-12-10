package model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import model.enumes.Degree;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student extends Person {
    String numberStudent;
    @ManyToOne(cascade = CascadeType.ALL)
    University university;
    Integer enterYear;
    @Enumerated(value = EnumType.STRING)
    Degree degree;
    @OneToOne(cascade = CascadeType.PERSIST)
    InfoAccount infoAccount;
    boolean dorm;
    @OneToOne(cascade = CascadeType.PERSIST)
    Address address;
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<StudentLoan> studentLoanList = new ArrayList<>();
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    Spouse spouse;
    @OneToOne(cascade = CascadeType.PERSIST)
    CreditCard creditCard;

    public Student(int id, String firstName, String lastName, String nameFather, String nameMother, String nationalCode, String numberIdentity, Date birthday, boolean isMarried, String numberStudent, University university, Integer enterYear, Degree degree, InfoAccount infoAccount, boolean dorm, Address address, List<StudentLoan> studentLoanList, Spouse spouse, CreditCard creditCard) {
        super(id, firstName, lastName, nameFather, nameMother, nationalCode, numberIdentity, birthday, isMarried);
        this.numberStudent = numberStudent;
        this.university = university;
        this.enterYear = enterYear;
        this.degree = degree;
        this.infoAccount = infoAccount;
        this.dorm = dorm;
        this.address = address;
        this.studentLoanList = studentLoanList;
        this.spouse = spouse;
        this.creditCard = creditCard;
    }
}

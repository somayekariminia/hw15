package model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import model.enumes.Degree;

import javax.persistence.*;
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
    @ManyToOne
    University university;
    Integer enterYear;
    @Enumerated
    Degree degree;
    @OneToOne
    InfoAccount infoAccount;
    boolean dorm;
    @OneToOne
    Address address;
    @OneToMany
    List<StudentLoan> studentLoanList;
}

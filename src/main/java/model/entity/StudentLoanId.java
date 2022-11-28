package model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentLoanId implements Serializable {
    Student student;
    Loan loan;
}

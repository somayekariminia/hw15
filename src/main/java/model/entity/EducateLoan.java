package model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import model.enumes.Degree;
import model.enumes.TypeLoan;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EducateLoan extends Loan {
    @Enumerated
    TypeLoan typeLoan;
    Degree degree;
}


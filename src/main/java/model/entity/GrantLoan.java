package model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import model.enumes.Degree;
import model.enumes.TypeLoan;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrantLoan extends Loan {
    @Enumerated(value = EnumType.STRING)
    TypeLoan typeLoan;
    Degree degree;
}


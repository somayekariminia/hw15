package model.entity;

import lombok.*;
import model.enumes.TypeCity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MortgageLoan extends Loan {
    @Enumerated(value= EnumType.STRING)
    TypeCity typeCity;
}

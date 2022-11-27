package model.entity;

import lombok.*;
import model.enumes.TypeCity;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MortgageLoan extends Loan {
 TypeCity typeCity;
}

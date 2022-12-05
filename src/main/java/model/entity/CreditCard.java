package model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreditCard {
    @Id
    int id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String cardNumber;
    String ccv2;
    String accountNumber;
    double balance;
    Date expireDate;
}

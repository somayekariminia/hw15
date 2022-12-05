package model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
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
    @Column(length = 16)
    String cardNumber;
    String ccv2;
    String accountNumber;
    double balance;
    Date expireDate;
}

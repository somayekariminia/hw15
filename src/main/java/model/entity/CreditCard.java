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
    @Column(length = 16, unique = true, nullable = false)
    String cardNumber;
    @Column(length = 4, nullable = false)
    String ccv2;
    @Column(length = 10, nullable = false)
    String accountNumber;
    double balance;
    @Temporal(value = TemporalType.TIMESTAMP)
    Date expireDate;
}

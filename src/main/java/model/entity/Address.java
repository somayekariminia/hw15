package model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int i;
    String country;
    String city;
    String Street;
    String allay;
    String plack;
}

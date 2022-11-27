package model.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Address {
    String city;
    String Street;
    String allay;
    String plack;
}

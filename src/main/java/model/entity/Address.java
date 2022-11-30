package model.entity;

import lombok.*;
import model.enumes.LargeCity;
import model.enumes.TypeCity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor

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

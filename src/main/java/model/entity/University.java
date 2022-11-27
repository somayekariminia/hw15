package model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import model.enumes.TypeUniversity;

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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String city;
    String name;
    TypeUniversity typeUniversity;
}

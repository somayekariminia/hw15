package model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Spouse extends Person {
    public Spouse(int id, String firstName, String lastName, String nameFather, String nameMother, String nationalCode, String numberIdentity, Date birthday, boolean isMarried) {
        super(id, firstName, lastName, nameFather, nameMother, nationalCode, numberIdentity, birthday, isMarried);
    }
}


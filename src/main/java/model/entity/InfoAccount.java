package model.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class InfoAccount {
    String userMName;
    String password;
}

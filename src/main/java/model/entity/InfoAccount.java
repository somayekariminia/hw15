package model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class InfoAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int i;
    @Column(length = 8)
    String userName;
    String password;
}

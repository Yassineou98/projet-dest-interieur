package com.ing.nzy.interieur.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Infraction extends BaseEntity {

    private String label;

    private Integer categorie;

    // si la peine est une amende donc on doit recuperer le montant de la ministere des finances et si il l'a paye ou pas.
    @Column(name = "type_peine", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypePeine typePeine;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "person_id")
    private Person person;

    private Boolean reglee;

}

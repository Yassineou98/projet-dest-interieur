package com.ing.nzy.interieur.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person extends BaseEntity {

    private String cin;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String job;
    private Boolean avisDeRecherche;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    private Set<Infraction> infractions = new HashSet<>();

    public void addInfraction(Infraction infraction) {
        infraction.setPerson(this);
        infractions.add(infraction);
    }
}

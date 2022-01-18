package com.ing.nzy.interieur.repository;

import com.ing.nzy.interieur.domain.Infraction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InfractionRepository extends JpaRepository<Infraction, UUID> {

    @EntityGraph(attributePaths = {"person"})
    List<Infraction> findAllByPersonId(UUID personId);

}

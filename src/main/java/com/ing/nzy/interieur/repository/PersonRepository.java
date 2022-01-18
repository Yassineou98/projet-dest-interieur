package com.ing.nzy.interieur.repository;

import com.ing.nzy.interieur.domain.Person;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {


    @EntityGraph(attributePaths = {"infractions"})
    Optional<Person> findById(UUID personId);

    @EntityGraph(attributePaths = {"infractions"})
    Optional<Person> findByCin(String cin);

}

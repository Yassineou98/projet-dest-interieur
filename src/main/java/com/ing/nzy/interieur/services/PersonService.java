package com.ing.nzy.interieur.services;


import com.ing.nzy.model.InfractionDto;
import com.ing.nzy.model.PersonDto;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    List<PersonDto> getAllPersons();

    List<PersonDto> getAllRecherchee();

    PersonDto getPersonInfoById(UUID personId);

    void addInfraction(UUID personId, InfractionDto infractionDto);

    void createAvisDeRecherche(String cin, UUID infractionId);

    void updateAvisDeRecherche(String cin);

}

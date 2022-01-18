package com.ing.nzy.interieur.services;


import com.ing.nzy.dto.InfractionDto;
import com.ing.nzy.dto.PersonDto;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    List<PersonDto> getAllPersons();

    List<PersonDto> getAllRecherchee();

    PersonDto getPersonInfoById(UUID personId);

    PersonDto getPersonInfoByCin(String cin);

    void addInfraction(String cin, InfractionDto infractionDto);

    void createAvisDeRecherche(String cin, UUID infractionId);

    void updateAvisDeRecherche(String cin);


}

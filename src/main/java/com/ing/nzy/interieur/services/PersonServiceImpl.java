package com.ing.nzy.interieur.services;

import com.ing.nzy.interieur.domain.Infraction;
import com.ing.nzy.interieur.domain.Person;
import com.ing.nzy.interieur.repository.InfractionRepository;
import com.ing.nzy.interieur.repository.PersonRepository;
import com.ing.nzy.interieur.services.amende.AmendService;
import com.ing.nzy.interieur.web.mappers.InfractionMapper;
import com.ing.nzy.interieur.web.mappers.PersonMapper;
import com.ing.nzy.dto.InfractionDto;
import com.ing.nzy.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.ing.nzy.interieur.domain.TypePeine.EMPRISONNEMENT;


@Service
@RequiredArgsConstructor
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final InfractionMapper infractionMapper;
    private final InfractionRepository infractionRepository;
    private final AmendService amendService;

    @Override
    public List<PersonDto> getAllPersons() {
        return personRepository
                .findAll()
                .stream()
                .map(personMapper::personToPersonDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonDto> getAllRecherchee() {

        return personRepository
                .findAll()
                .stream()
                .filter(Person::getAvisDeRecherche)
                .map(personMapper::personToPersonDto)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto getPersonInfoById(UUID personId) {

        Person person = getPersonById(personId);
        return personMapper.personToPersonDto(person);
    }

    @Override
    public PersonDto getPersonInfoByCin(String cin) {
        Person person = getPersonByCin(cin);
        return personMapper.personToPersonDto(person);
    }

    @Override
    public void addInfraction(String cin, InfractionDto infractionDto) {

        Person person = getPersonByCin(cin);

        Infraction infraction = infractionMapper.infractionDtoToInfraction(infractionDto);

        infraction.setReglee(false);

        person.addInfraction(infraction);

        infraction.setPerson(person);

        person.setAvisDeRecherche(
                infraction.getTypePeine() == EMPRISONNEMENT
        );

        amendService.createAmend(infractionMapper.infractionToInfractionDto(infractionRepository.saveAndFlush(infraction)));

    }

    @Override
    public void createAvisDeRecherche(String cin, UUID infractionId) {

        System.err.println(cin);
        Person person = getPersonByCin(cin);
        person.setAvisDeRecherche(true);
        personRepository.saveAndFlush(person);

    }

    @Override
    public void updateAvisDeRecherche(String cin) {

        Person person = getPersonByCin(cin);

        long infractionEmprisonnementNonRegleeCount = person.getInfractions()
                .stream()
                .filter(infraction -> !infraction.getReglee() && infraction.getTypePeine().equals(EMPRISONNEMENT))
                .count();

        Boolean isRecherchee = infractionEmprisonnementNonRegleeCount > 0;

        person.setAvisDeRecherche(isRecherchee);

        personRepository.saveAndFlush(person);
    }

    private Person getPersonById(UUID personId) {
        Optional<Person> personOptional = personRepository.findById(personId);

        if (personOptional.isEmpty()) {
            throw new RuntimeException("Person Not Found");
        }

        return personOptional.get();
    }

    private Person getPersonByCin(String cin) {
        Optional<Person> personOptional = personRepository.findByCin(cin);

        if (personOptional.isEmpty()) {
            throw new RuntimeException("Person Not Found");
        }

        return personOptional.get();
    }



}

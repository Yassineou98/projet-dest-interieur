package com.ing.nzy.interieur.web.mappers;

import com.ing.nzy.interieur.domain.Person;
import com.ing.nzy.model.PersonDto;
import org.mapstruct.Mapper;

@Mapper(uses = {InfractionMapper.class, DateMapper.class})
public interface PersonMapper {

    PersonDto personToPersonDto(Person person);

    Person personDtoToPerson(PersonDto personDto);

}

package com.ing.nzy.interieur.web.mappers;

import com.ing.nzy.interieur.domain.Person;
import com.ing.nzy.dto.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(uses = {InfractionMapper.class, DateMapper.class}, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PersonMapper {

    PersonDto personToPersonDto(Person person);

    Person personDtoToPerson(PersonDto personDto);

}

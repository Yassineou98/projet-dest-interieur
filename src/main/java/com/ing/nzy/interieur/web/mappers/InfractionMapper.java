package com.ing.nzy.interieur.web.mappers;

import com.ing.nzy.interieur.domain.Infraction;
import com.ing.nzy.model.InfractionDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;


@Mapper(uses = {PersonMapper.class, DateMapper.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface InfractionMapper {

    @Mapping(source = "person.infractions", target = "person.infractions", ignore = true)
    InfractionDto infractionToInfractionDto(Infraction infraction);

    @InheritInverseConfiguration
    @Mapping(source = "createdDate", ignore = true, target = "createdDate")
    Infraction infractionDtoToInfraction(InfractionDto infractionDto);

}

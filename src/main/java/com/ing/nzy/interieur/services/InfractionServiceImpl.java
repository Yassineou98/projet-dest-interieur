package com.ing.nzy.interieur.services;

import com.ing.nzy.interieur.domain.Infraction;
import com.ing.nzy.interieur.domain.TypePeine;
import com.ing.nzy.interieur.repository.InfractionRepository;
import com.ing.nzy.interieur.web.mappers.InfractionMapper;
import com.ing.nzy.dto.AmendDto;
import com.ing.nzy.dto.InfractionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class InfractionServiceImpl implements InfractionService {

    private final InfractionRepository infractionRepository;
    private final InfractionMapper infractionMapper;


    @Override
    public void updateInfraction(InfractionDto infractionDto) {
        Optional<Infraction> infractionOptional = infractionRepository.findById(infractionDto.getId());

        if (infractionOptional.isEmpty()) {
            throw new RuntimeException("NO INFRACTION WITH THIS ID");
        }

        Infraction infraction = infractionOptional.get();
        updateInfraction(infraction, infractionDto);
        infractionRepository.saveAndFlush(infraction);

    }

    @Override
    public void reglerInfraction(UUID infractionId) {

        Infraction infraction = infractionRepository.findById(infractionId).orElseThrow(RuntimeException::new);

        infraction.setReglee(true);

        infractionRepository.saveAndFlush(infraction);

    }

    @Override
    public void deleteInfraction(UUID infractionId) {
        infractionRepository.deleteById(infractionId);
    }

    @Override
    public void infractionNotPayedAfterDeadline(AmendDto amendeDto) {

        Infraction infraction = infractionRepository.findById(amendeDto.getInfractionId()).orElseThrow(RuntimeException::new);

        infraction.setTypePeine(TypePeine.EMPRISONNEMENT);

        infraction.getPerson().setAvisDeRecherche(true);

        infractionRepository.saveAndFlush(infraction);

    }

    @Override
    public InfractionDto getInfractionById(UUID id) {
        return infractionMapper.infractionToInfractionDto(infractionRepository.findById(id).get());
    }

    private void updateInfraction(Infraction infraction, InfractionDto infractionDto) {
        infraction.setTypePeine(TypePeine.valueOf(infractionDto.getTypePeine()));
        infraction.setCategorie(infractionDto.getCategorie());
        infraction.setLabel(infractionDto.getLabel());
    }
}

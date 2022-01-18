package com.ing.nzy.interieur.services.amende;

import com.ing.nzy.interieur.config.JmsConfig;
import com.ing.nzy.interieur.services.InfractionService;
import com.ing.nzy.interieur.services.PersonService;
import com.ing.nzy.dto.AmendDto;
import com.ing.nzy.dto.messages.PayementAmendeEvent;
import com.ing.nzy.dto.messages.RechercheCreationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MinistereFinanceListener {

    private final PersonService personService;
    private final InfractionService infractionService;

    @JmsListener(destination = JmsConfig.CREATION_RECHERCHE_TOPIC)
    public void listenAddAvisRecherche(RechercheCreationEvent rechercheCreationEvent) {

        AmendDto amendDto = rechercheCreationEvent.getAmendeDto();

        personService.createAvisDeRecherche(amendDto.getCin(), amendDto.getInfractionId());
    }

    @JmsListener(destination = JmsConfig.REGLEE_AMENDE_TOPIC)
    public void listenPayAmend(PayementAmendeEvent amendPayedMessage) {

        AmendDto amendDto = amendPayedMessage.getAmendDto();
        infractionService.reglerInfraction(amendDto.getInfractionId());
        personService.updateAvisDeRecherche(amendDto.getCin());

    }
}

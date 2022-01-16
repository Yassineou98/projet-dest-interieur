package com.ing.nzy.interieur.services.amende;

import com.ing.nzy.interieur.config.JmsConfig;
import com.ing.nzy.interieur.services.InfractionService;
import com.ing.nzy.interieur.services.PersonService;
import com.ing.nzy.model.AmendDto;
import com.ing.nzy.model.messages.AmendPayedMessage;
import com.ing.nzy.model.messages.CreateRechercheMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MinistereFinanceListener {

    private final PersonService personService;
    private final InfractionService infractionService;

    @JmsListener(destination = JmsConfig.CREATE_RECHERCHE_QUEUE)
    public void listenAddAvisRecherche(CreateRechercheMessage createRechercheMessage) {

        AmendDto amendDto = createRechercheMessage.getAmendeDto();

        personService.createAvisDeRecherche(amendDto.getCin(), amendDto.getInfractionId());
    }

    @JmsListener(destination = JmsConfig.PAY_AMEND_QUEUE)
    public void listenPayAmend(AmendPayedMessage amendPayedMessage) {

        AmendDto amendDto = amendPayedMessage.getAmendDto();
        infractionService.reglerInfraction(amendDto.getInfractionId());
        personService.updateAvisDeRecherche(amendDto.getCin());

    }
}

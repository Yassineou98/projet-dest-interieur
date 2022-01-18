package com.ing.nzy.interieur.services.amende;

import com.ing.nzy.interieur.config.JmsConfig;
import com.ing.nzy.interieur.services.InfractionService;
import com.ing.nzy.dto.AmendDto;
import com.ing.nzy.dto.InfractionDto;
import com.ing.nzy.dto.messages.AmendeCreationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AmendServiceImpl implements AmendService {


    private final JmsTemplate jmsTemplate;
    private final InfractionService infractionService;

    @Override
    public void createAmend(InfractionDto infractionDto) {
        jmsTemplate.convertAndSend(JmsConfig.CREATION_AMENDE_TOPIC,
                AmendeCreationEvent.builder().infractionDto(infractionDto).build());
    }

    @Override
    public void createRecherche(AmendDto amendDto) {
        infractionService.infractionNotPayedAfterDeadline(amendDto);
    }
}
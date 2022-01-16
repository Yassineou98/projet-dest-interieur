package com.ing.nzy.interieur.services;


import com.ing.nzy.model.AmendDto;
import com.ing.nzy.model.InfractionDto;

import java.util.UUID;

public interface InfractionService {

    void updateInfraction(InfractionDto infractionDto);

    void reglerInfraction(UUID infractionId);

    void deleteInfraction(UUID infractionId);

    void infractionNotPayedAfterDeadline(AmendDto amendDto);

    InfractionDto getInfractionById(UUID id);
}

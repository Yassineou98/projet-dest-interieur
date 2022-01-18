package com.ing.nzy.interieur.services.amende;


import com.ing.nzy.dto.AmendDto;
import com.ing.nzy.dto.InfractionDto;

public interface AmendService {
    void createAmend(InfractionDto infractionDto);

    void createRecherche(AmendDto amendDto);


}

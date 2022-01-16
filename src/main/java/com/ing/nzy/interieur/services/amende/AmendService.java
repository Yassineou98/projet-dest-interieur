package com.ing.nzy.interieur.services.amende;


import com.ing.nzy.model.AmendDto;
import com.ing.nzy.model.InfractionDto;

public interface AmendService {
    void createAmend(InfractionDto infractionDto);

    void createRecherche(AmendDto amendDto);


}

package com.ing.nzy.model.messages;

import com.ing.nzy.model.AmendDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRechercheMessage implements Serializable {

    static final long serialVersionUID = 5976611858274461138L;

    private AmendDto amendeDto;

}

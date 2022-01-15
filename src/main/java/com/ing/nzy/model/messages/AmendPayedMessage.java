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
public class AmendPayedMessage implements Serializable {

    static final long serialVersionUID = 373535592915511915L;

    private AmendDto amendDto;

}

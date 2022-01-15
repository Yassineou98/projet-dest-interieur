package com.ing.nzy.model.messages;

import com.ing.nzy.model.InfractionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAmendMessage implements Serializable {

    static final long serialVersionUID = -8748289177798674896L;

    private InfractionDto infractionDto;

}

package com.ing.nzy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AmendDto {

    private String id;
    private UUID infractionId;
    private BigDecimal amount;
    private String cin;
    private LocalDate creationDate;
    private LocalDate dueDate;
    private String remarks;
    private Boolean isPayed;

}

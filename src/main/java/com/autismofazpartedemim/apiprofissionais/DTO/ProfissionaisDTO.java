package com.autismofazpartedemim.apiprofissionais.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfissionaisDTO {
    @NotBlank
    private String nome;
    @NotBlank
    private String endereço;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;

}

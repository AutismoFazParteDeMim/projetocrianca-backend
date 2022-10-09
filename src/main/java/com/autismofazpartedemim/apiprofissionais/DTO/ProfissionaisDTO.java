package com.autismofazpartedemim.apiprofissionais.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;
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
    @Digits(integer = 4, fraction = 7)
    private BigDecimal latitude;
    @NotNull
    @Digits(integer = 4, fraction = 7)
    private BigDecimal longitude;
    @NotBlank
    private String contato;
    @NotBlank
    private String horarioFuncionamento;
    @NotBlank
    private String diasFuncionamento;

    private String descricao;
    @NotBlank
    @NotNull
    private String email;

}

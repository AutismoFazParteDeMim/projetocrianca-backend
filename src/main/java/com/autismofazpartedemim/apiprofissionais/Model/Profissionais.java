package com.autismofazpartedemim.apiprofissionais.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import java.math.BigDecimal;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_profissionais")
public class Profissionais {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String nome;
    @Column
    private String endereço;
    @Column
    @Digits(integer = 4, fraction = 7)
    private BigDecimal latitude;
    @Column
    @Digits(integer = 4, fraction = 7)
    private BigDecimal longitude;
    @Column
    private String contato;
    @Column
    private String horarioFuncionamento;
    @Column
    private String diasFuncionamento;
    @Column
    private String descricao;
    @Column
    private String email;
}

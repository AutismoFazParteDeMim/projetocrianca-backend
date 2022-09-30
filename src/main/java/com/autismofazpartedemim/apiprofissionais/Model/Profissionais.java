package com.autismofazpartedemim.apiprofissionais.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Double latitude;
    @Column
    private Double longitude;

}

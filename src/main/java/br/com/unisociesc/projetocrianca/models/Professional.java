package br.com.unisociesc.projetocrianca.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "professionals")
public class Professional {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(nullable = false)
    private String email;

    @Digits(integer = 4, fraction = 7)
    @Column(nullable = false)
    private BigDecimal latitude;

    @Column(nullable = false)
    @Digits(integer = 4, fraction = 7)
    private BigDecimal longitude;

    @Column(name = "opening_hours", nullable = false)
    private String openingHours;

    @Column(name = "work_days", nullable = false)
    private String workDays;

    private String description;
}

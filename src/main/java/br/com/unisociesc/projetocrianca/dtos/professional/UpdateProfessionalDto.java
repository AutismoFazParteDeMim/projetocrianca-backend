package br.com.unisociesc.projetocrianca.dtos.professional;

import java.math.BigDecimal;

import jakarta.validation.constraints.Email;
import lombok.Builder;

@Builder
public record UpdateProfessionalDto(String name,
    String address,
    String workDays,
    String openingHours,
    String contactNumber,
    @Email String email,
    String description,
    BigDecimal latitude,
    BigDecimal longitude) {
}

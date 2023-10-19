package br.com.unisociesc.projetocrianca.dtos.professional;

import java.math.BigDecimal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateProfessionalDto(
    @NotNull String name,
    @NotNull String address,
    @NotNull String workDays,
    @NotNull String openingHours,
    @NotNull String contactNumber,
    @Email String email,
    String description,
    @NotBlank BigDecimal latitude,
    @NotBlank BigDecimal longitude) {

}

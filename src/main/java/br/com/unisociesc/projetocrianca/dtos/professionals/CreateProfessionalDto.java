package br.com.unisociesc.projetocrianca.dtos.professionals;

import java.math.BigDecimal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateProfessionalDto(
        @NotBlank String name,
        @NotBlank String address,
        @NotBlank String workDays,
        @NotBlank String openingHours,
        @NotBlank String contactNumber,
        @Email String email,
        String description,
        @NotNull BigDecimal latitude,
        @NotNull BigDecimal longitude) {

}

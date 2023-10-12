package br.com.unisociesc.projetocrianca.dtos.professional;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProfessionalDto(
    @NotNull String name,
    @NotNull String address,
    @NotNull String workDays,
    @NotNull String openingHours,
    @NotNull String contactNumber,
    String email,
    String description,
    @NotBlank Long latitude,
    @NotBlank Long longitude) {

}

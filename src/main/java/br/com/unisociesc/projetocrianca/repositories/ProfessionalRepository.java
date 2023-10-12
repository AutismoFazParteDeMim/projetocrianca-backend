package br.com.unisociesc.projetocrianca.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unisociesc.projetocrianca.models.Professional;

public interface ProfessionalRepository extends JpaRepository<Professional, UUID> {
}

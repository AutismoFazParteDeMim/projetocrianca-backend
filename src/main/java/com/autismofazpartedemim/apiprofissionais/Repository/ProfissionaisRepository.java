package com.autismofazpartedemim.apiprofissionais.Repository;

import com.autismofazpartedemim.apiprofissionais.Model.Profissionais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionaisRepository extends JpaRepository<Profissionais, Long> {
    boolean existsByNome(String name);
}

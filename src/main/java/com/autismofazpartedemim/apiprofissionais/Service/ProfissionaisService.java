package com.autismofazpartedemim.apiprofissionais.Service;

import com.autismofazpartedemim.apiprofissionais.DTO.ProfissionaisDTO;
import com.autismofazpartedemim.apiprofissionais.Model.Profissionais;
import com.autismofazpartedemim.apiprofissionais.Repository.ProfissionaisRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfissionaisService {

    final ProfissionaisRepository pr;

    public ResponseEntity<Object> post(Profissionais profissionais) {
        if (pr.existsByNome(profissionais.getNome())) {
            return  ResponseEntity.status(HttpStatus.CONFLICT).body("Esse Profissional já está cadastrado no Sistema");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pr.save(profissionais));
    }

    public ResponseEntity<List<Profissionais>> getAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(pr.findAll());
    }

    public ResponseEntity<Object> getById(Long id) {
        Optional<Profissionais> profissionaisOptional = pr.findById(id);
        if (!profissionaisOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profissional não encontrado");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(profissionaisOptional.get());
    }
}


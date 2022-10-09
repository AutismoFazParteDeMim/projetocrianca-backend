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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class ProfissionaisService {

    final ProfissionaisRepository pr;

    public ResponseEntity<Object> post(Profissionais profissionais) {
        if (pr.existsByNome(profissionais.getNome())) {
            return  ResponseEntity.status(HttpStatus.CONFLICT).body("Esse Profissional já está cadastrado no Sistema");
        }
        if (!validate(profissionais.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email Invalido");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pr.save(profissionais));
    }

    public ResponseEntity<List<Profissionais>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(pr.findAll());
    }

    public ResponseEntity<Object> getById(Long id) {
        Optional<Profissionais> profissionaisOptional = pr.findById(id);
        if (!profissionaisOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profissional não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(profissionaisOptional.get());
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}


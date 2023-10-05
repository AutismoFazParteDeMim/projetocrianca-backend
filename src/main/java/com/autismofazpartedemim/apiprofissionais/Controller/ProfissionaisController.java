package com.autismofazpartedemim.apiprofissionais.Controller;

import com.autismofazpartedemim.apiprofissionais.DTO.ProfissionaisDTO;
import com.autismofazpartedemim.apiprofissionais.Model.Profissionais;

import com.autismofazpartedemim.apiprofissionais.Service.ProfissionaisService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/autismofazpartedemim")
public class ProfissionaisController {

    @Autowired
    private ProfissionaisService service;

    @RequestMapping("/status")
    public String status() {
        String funcionamento = "Estamos Funcionando com 100% de força";

        return funcionamento;
    }

    @PostMapping("/posting/admin")
    public ResponseEntity<Object> postProfissionais(@RequestBody @Valid ProfissionaisDTO profissionaisDTO) {

        Profissionais profissionais = new Profissionais();
        BeanUtils.copyProperties(profissionaisDTO, profissionais);
        return service.post(profissionais);
    }

    @GetMapping("/profissionais")
    public ResponseEntity<List<Profissionais>> getTodosProfissionais() {
        return service.getAll();
    }

    @GetMapping("/profissionais/{id}")
    public ResponseEntity<Object> getProfissionalId (Long id) {
        return service.getById(id);
    }
}

package br.com.unisociesc.projetocrianca.modules.professionals;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unisociesc.projetocrianca.dtos.professionals.CreateProfessionalDto;
import br.com.unisociesc.projetocrianca.dtos.professionals.UpdateProfessionalDto;
import br.com.unisociesc.projetocrianca.models.Professional;
import br.com.unisociesc.projetocrianca.modules.professionals.services.ProfessionalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/professionals")
public class ProfessionalController {

  @Autowired
  ProfessionalService professionalService;

  @GetMapping()
  public ResponseEntity<List<Professional>> getAllProfessionals() {
    var professionals = professionalService.getAllProfessionals();
    return ResponseEntity.status(HttpStatus.OK).body(professionals);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Professional> getProfessionalById(@RequestParam(name = "id") UUID id) {
    var professional = professionalService.getProfessionalById(id);
    return ResponseEntity.status(HttpStatus.OK).body(professional);
  }

  @PostMapping()
  public ResponseEntity<Void> createProfessional(@RequestBody @Valid CreateProfessionalDto dto) {
    professionalService.createProfessional(dto);
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateProfessional(@RequestParam(name = "id") UUID id,
      @RequestBody @Valid UpdateProfessionalDto dto) {
    professionalService.updateProfessional(id, dto);
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteProfessional(@RequestParam(name = "id") UUID id) {
    professionalService.deleteProfessional(id);
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}

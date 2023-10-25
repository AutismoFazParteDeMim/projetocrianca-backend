package br.com.unisociesc.projetocrianca.modules.professionals.services;

import br.com.unisociesc.projetocrianca.Utils;
import br.com.unisociesc.projetocrianca.dtos.professionals.CreateProfessionalDto;
import br.com.unisociesc.projetocrianca.dtos.professionals.UpdateProfessionalDto;
import br.com.unisociesc.projetocrianca.models.Professional;
import br.com.unisociesc.projetocrianca.modules.ModuleHelper;
import br.com.unisociesc.projetocrianca.modules.professionals.errors.ProfessionalCopyException;
import br.com.unisociesc.projetocrianca.modules.professionals.errors.ProfessionalNotFoundException;
import br.com.unisociesc.projetocrianca.repositories.ProfessionalRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProfessionalService {
    @Autowired
    ProfessionalRepository professionalRepository;

    @Autowired
    ModuleHelper helper;

    public List<Professional> getAllProfessionals() {
        var professionals = professionalRepository.findAll();
        return professionals;
    }

    public Professional getProfessionalById(UUID id) {
        var professional = helper.findByUUID(id, professionalRepository, Professional.class);
        return professional;
    }

    public void createProfessional(CreateProfessionalDto dto) {
        var professional = new Professional();
        try {
            BeanUtils.copyProperties(dto, professional);
            professionalRepository.save(professional);
        } catch (BeansException error) {
            throw new ProfessionalCopyException(Professional.class, CreateProfessionalDto.class, error);
        }

    }

    public void updateProfessional(UUID professionalId, UpdateProfessionalDto dto) {
        var professional = helper.findByUUID(professionalId, professionalRepository, Professional.class);
        try {
            BeanUtils.copyProperties(dto, professional, Utils.getNullPropertyNames(dto));
            professionalRepository.saveAndFlush(professional);
        } catch (BeansException error) {
            throw new ProfessionalCopyException(Professional.class, CreateProfessionalDto.class, error);
        }
    }

    public void deleteProfessional(UUID id) {
        if (!professionalRepository.existsById(id)) {
            throw new ProfessionalNotFoundException(Professional.class, "id", id.toString());
        }
        professionalRepository.deleteById(id);
    }

}

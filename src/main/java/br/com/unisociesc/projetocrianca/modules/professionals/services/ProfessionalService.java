package br.com.unisociesc.projetocrianca.modules.professionals.services;

import br.com.unisociesc.projetocrianca.dtos.professional.CreateProfessionalDto;
import br.com.unisociesc.projetocrianca.dtos.professional.UpdateProfessionalDto;
import br.com.unisociesc.projetocrianca.models.Professional;
import br.com.unisociesc.projetocrianca.modules.ModuleHelper;
import br.com.unisociesc.projetocrianca.modules.professionals.errors.ProfessionalNotFoundException;
import br.com.unisociesc.projetocrianca.repositories.ProfessionalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    }

    public void updateProfessional(UpdateProfessionalDto dto) {

    }

    public void deleteProfessional(UUID id) {
        if (!professionalRepository.existsById(id)) {
            throw new ProfessionalNotFoundException(Professional.class, id, id.toString());
        }
        professionalRepository.deleteById(id);
    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

}

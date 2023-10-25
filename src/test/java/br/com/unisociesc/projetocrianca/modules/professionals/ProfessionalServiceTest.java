package br.com.unisociesc.projetocrianca.modules.professionals;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.unisociesc.projetocrianca.dtos.professionals.CreateProfessionalDto;
import br.com.unisociesc.projetocrianca.dtos.professionals.UpdateProfessionalDto;
import br.com.unisociesc.projetocrianca.models.Professional;
import br.com.unisociesc.projetocrianca.modules.ModuleHelper;
import br.com.unisociesc.projetocrianca.modules.professionals.errors.ProfessionalNotFoundException;
import br.com.unisociesc.projetocrianca.modules.professionals.services.ProfessionalService;
import br.com.unisociesc.projetocrianca.repositories.ProfessionalRepository;

@ExtendWith(MockitoExtension.class)
public class ProfessionalServiceTest {
  @Mock
  ProfessionalRepository professionalRepository;

  @Spy
  ModuleHelper helper;

  @InjectMocks
  ProfessionalService service;

  private Professional professionalModel;

  private ArgumentCaptor<Professional> captureProfessional;

  @BeforeEach
  public void setUp() {
    professionalModel = Professional.builder()
        .address("rua test")
        .contactNumber("contact_number")
        .description("description")
        .email("test@test.com")
        .latitude(new BigDecimal(-26.2947030))
        .longitude(new BigDecimal(-48.8481450))
        .name("mock_name")
        .openingHours("14:00")
        .workDays("seg-sex")
        .build();

    captureProfessional = ArgumentCaptor.forClass(Professional.class);

  }

  private List<Object> mountProperties(Professional model) {
    var properties = new ArrayList<>();
    properties.add(model.getAddress());
    properties.add(model.getContactNumber());
    properties.add(model.getDescription());
    properties.add(model.getEmail());
    properties.add(model.getName());
    properties.add(model.getOpeningHours());
    properties.add(model.getWorkDays());
    properties.add(model.getLatitude());
    properties.add(model.getLongitude());
    return properties;
  }

  @Test
  public void createProfessionalSucc() {
    var dto = CreateProfessionalDto.builder()
        .address("rua test")
        .contactNumber("contact_number")
        .description("description")
        .email("test@test.com")
        .latitude(new BigDecimal(-26.2947030))
        .longitude(new BigDecimal(-48.8481450))
        .name("mock_name")
        .openingHours("14:00")
        .workDays("seg-sex")
        .build();

    service.createProfessional(dto);

    verify(professionalRepository).save(captureProfessional.capture());

    var result = captureProfessional.getValue();
    var resultProperties = mountProperties(result);
    var modelProperties = mountProperties(professionalModel);

    assertEquals(modelProperties, resultProperties);
  }

  @Test
  public void updateProfessionalSucc() {
    var dto = UpdateProfessionalDto.builder()
        .name("teste name")
        .build();
    var oldName = professionalModel.getName();
    professionalModel.setId(UUID.randomUUID());
    var professionalId = professionalModel.getId();

    doReturn(professionalModel).when(this.helper).findByUUID(professionalId, professionalRepository,
        Professional.class);

    service.updateProfessional(professionalId, dto);

    verify(professionalRepository).saveAndFlush(captureProfessional.capture());
    var result = captureProfessional.getValue();

    assertNotEquals(oldName, result.getName());
    assertEquals(professionalId, result.getId());
  }

  @Test
  public void updateProfessionalWithEmptyDto() {

  }

  @Test
  public void deleteProfessionalSucc() {
    var professionalId = UUID.randomUUID();
    when(professionalRepository.existsById(professionalId)).thenReturn(true);

    assertAll(() -> service.deleteProfessional(professionalId));

  }

  @Test
  public void deleteProfessionalFail() {
    var professionalId = UUID.randomUUID();
    when(professionalRepository.existsById(professionalId)).thenReturn(false);

    assertThrows(ProfessionalNotFoundException.class, () -> service.deleteProfessional(professionalId));
  }

}

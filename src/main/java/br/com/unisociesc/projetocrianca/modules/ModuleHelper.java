package br.com.unisociesc.projetocrianca.modules;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.com.unisociesc.projetocrianca.modules.professionals.errors.ProfessionalNotFoundException;

@Component
public class ModuleHelper {
  private ModuleHelper() {
  };

  /**
   * Method to check what JpaRepository default method finById return.
   * 
   * @param <T>       - Generic type of entity.
   * @param entity    - Must be Optional.
   * @param paramName - Must be not blank.
   * @param param     - Must be not blank.
   * @throws ShopNotFoundException if entity is empty.
   * @author Brian Duarte
   */
  public <T, C> void checkFindById(Optional<T> entity, Class<C> className, String paramName, String param) {
    if (entity.isEmpty()) {
      throw new ProfessionalNotFoundException(className, paramName, param);
    }
  }

  /**
   * Method to check what JpaRepository custom query return.
   * 
   * @param <L>       - Generic type of list.
   * @param <C>       - Generic type of Class.
   * @param entity    - Must be Optional.
   * @param className - Must be class keyType (Class.class).
   * @param paramName - Must be not blank.
   * @param param     - Must be not blank.
   * @throws ShopNotFoundException if a list of entity is empty.
   * @author Brian Duarte
   */
  public <L, C> void checkFindByIdList(List<L> entity, Class<C> className, String paramName, String param) {
    if (entity.isEmpty()) {
      throw new ProfessionalNotFoundException(className, paramName, param);
    }
  }

  /**
   * Method to substitute default method findById of JpaRepository.
   * 
   * @param <T>        - Type of repository uses
   * @param <C>        - Type of class of model
   * @param id         - Must be not null [UUID]
   * @param repository - Repository where search
   * @return Entire entity
   * @author Brian Duarte
   */
  public <T, C> T findByUUID(UUID id, JpaRepository<T, UUID> repository, Class<C> className) {
    var entity = repository.findById(id);
    checkFindById(entity, className, "id", id.toString());
    return entity.get();
  }
}

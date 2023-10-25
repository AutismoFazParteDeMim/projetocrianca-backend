package br.com.unisociesc.projetocrianca.modules.professionals.errors;

public class ProfessionalNotFoundException extends ProfessionalException {

  @Override
  public String getCode() {
    return "404";
  }

  public ProfessionalNotFoundException(Class<?> clazz, String message, Throwable cause, Object... paramsMap) {
    super(clazz, message, cause, paramsMap);
  }

  public ProfessionalNotFoundException(Class<?> clazz, Object... paramsMap) {
    super(clazz, " was not found by parameter(s): ", paramsMap);
  }

}

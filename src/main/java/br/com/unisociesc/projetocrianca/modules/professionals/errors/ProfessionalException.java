package br.com.unisociesc.projetocrianca.modules.professionals.errors;

import br.com.unisociesc.projetocrianca.errors.DefaultException;

public class ProfessionalException extends DefaultException {

  public ProfessionalException(Class<?> clazz, String message, Throwable cause, Object... paramsMap) {
    super(clazz, message, cause, paramsMap);
  }

  public ProfessionalException(Class<?> clazz, String message, Object... paramsMap) {
    super(clazz, message, paramsMap);
  }

}

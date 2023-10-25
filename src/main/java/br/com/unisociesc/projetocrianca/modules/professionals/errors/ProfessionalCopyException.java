package br.com.unisociesc.projetocrianca.modules.professionals.errors;

public class ProfessionalCopyException extends ProfessionalException {

  @Override
  public String getCode() {
    return "500";
  }

  public <C, D> ProfessionalCopyException(Class<C> clazz, Class<D> dto, Throwable cause, Object... paramsMap) {
    super(clazz,
        String.format(" was not updated, error in copy properties from %s to %s.", clazz.getClass(), dto.getClass()),
        cause, paramsMap);
  }

  public ProfessionalCopyException(Class<?> clazz, String message, Object... paramsMap) {
    super(clazz, message, paramsMap);
  }

}

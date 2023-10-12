package br.com.unisociesc.projetocrianca.errors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import br.com.unisociesc.projetocrianca.LowerCaseClassNameResolver;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

import jakarta.validation.ConstraintViolation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
@Slf4j
public class ApiError {
  private HttpStatus status;
  private String code = "001";
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime timestamp;
  private String message;
  private String debugMessage;
  private List<ApiSubError> subErrors;

  private ApiError() {
    timestamp = LocalDateTime.now();
  }

  public ApiError(HttpStatus status) {
    this();
    this.status = status;
  }

  public ApiError(HttpStatus status, Throwable exception) {
    this();
    this.status = status;
    this.message = "Unexpected Error";
    this.debugMessage = exception.getLocalizedMessage();
    log.error(debugMessage, exception);
  }

  public ApiError(HttpStatus status, String message, Throwable exception) {
    this();
    this.status = status;
    this.message = message;
    this.debugMessage = exception.getLocalizedMessage();
    log.error(debugMessage, exception);
  }

  private void addSubError(ApiSubError subError) {
    if (subErrors == null) {
      subErrors = new ArrayList<>();
    }
    subErrors.add(subError);
  }

  private void addValidationError(String object, String field, Object rejectedValue, String message) {
    addSubError(new ApiValidationError(object, field, rejectedValue, message));
  }

  private void addValidationError(String object, String message) {
    addSubError(new ApiValidationError(object, message));
  }

  private void addValidationError(FieldError fieldError) {
    this.addValidationError(
        fieldError.getObjectName(),
        fieldError.getField(),
        fieldError.getRejectedValue(),
        fieldError.getDefaultMessage());
  }

  public void addValidationErrorByFields(List<FieldError> fieldErrors) {
    fieldErrors.forEach(this::addValidationError);
  }

  private void addValidationError(ObjectError objectError) {
    this.addValidationError(
        objectError.getObjectName(),
        objectError.getDefaultMessage());
  }

  public void addValidationErrorByObject(List<ObjectError> globalErrors) {
    globalErrors.forEach(this::addValidationError);
  }

  private void addValidationError(ConstraintViolation<?> constraintViolation) {
    this.addValidationError(
        constraintViolation.getRootBeanClass().getSimpleName(),
        ((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().asString(),
        constraintViolation.getInvalidValue(),
        constraintViolation.getMessage());
  }

  public void addValidationError(Set<ConstraintViolation<?>> constrains) {
    constrains.forEach(this::addValidationError);
  }
}

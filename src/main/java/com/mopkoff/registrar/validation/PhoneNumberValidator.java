package com.mopkoff.registrar.validation;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.mopkoff.registrar.annotation.PhoneNumberValid;
import com.mopkoff.registrar.model.domain.PhoneNumber;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberValid, String> {

  private final PhoneNumberUtil phoneNumberUtil;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

    var countryCode = Integer.parseInt(value.replaceAll(" \\d+", "").replace("+", ""));
    return !phoneNumberUtil.getRegionCodesForCountryCode(countryCode).isEmpty();
  }
}

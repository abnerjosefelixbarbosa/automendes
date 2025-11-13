import { ValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';

export function blankValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const value = control.value as string;
    const regex = /^[^\s]+$/;

    if (!value) { 
      return null;
    }

    return regex.test(value) ? null : { isBlank: true }
  };
}

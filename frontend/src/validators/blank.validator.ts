import { ValidatorFn, AbstractControl, ValidationErrors } from "@angular/forms";

export function blankValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const value = control.value as string;

    if (!value) return null; 
    
    const regex = /^[^\s]+$/;
    
    return regex.test(value)
      ? null
      : { isBlank: true }; 
  };
}
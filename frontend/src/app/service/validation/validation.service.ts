import { Injectable } from '@angular/core';
import { AbstractControl } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ValidationService {
  static getErrorMessage(control: AbstractControl | null, label:string): string {
    if (!control) {
      return ''
    }

    if (control?.hasError('required')) {
      return `${label} não deve ser vazio.`;
    }

    if (control?.hasError('isBlank')) {
      return `${label} não deve ter espaço vazio.`;
    }

    if (control?.hasError('maxlength')) {
      const maxLength = control.errors?.['maxlength'].requiredLength;
      return `${label} não deve ter mais de ${maxLength} caracteres.`;
    }

    return '';
  }
}

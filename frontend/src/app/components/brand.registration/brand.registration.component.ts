import { Component, inject } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { BrandRequest, BrandService } from '../../service/brand/brand.service';
import { HttpErrorResponse } from '@angular/common/http';
import { blankValidator } from '../../../validators/blank.validator';

@Component({
  selector: 'app-brand-registration',
  imports: [NavbarComponent, ReactiveFormsModule],
  templateUrl: './brand.registration.component.html',
  styleUrl: './brand.registration.component.css',
})
export class BrandRegistrationComponent {
  private brandService = inject(BrandService);
  form = new FormGroup(
    {
      name: new FormControl('', [
        Validators.required,
        Validators.maxLength(30),
        blankValidator()
      ]),
    }
  );
  message: string = '';
  messageError: string = '';

  constructor() {}

  registerBrand(data: FormGroup) {
    this.cleanError();
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const request: BrandRequest = {
      name: data.get('name')?.value,
    };

    this.brandService
      .registerBrand(request)
      .then(() => {
        this.message = 'Marca registrada.';
      })
      .catch((e: HttpErrorResponse) => {
        if (e.error) {
          this.messageError = e.error.message;
        }
      });
  }

  getErrorMessage(controlName: string) {
    const control = this.form.get(controlName);

    if (control?.hasError('required')) {
      return 'Nome não deve ser vazio.';
    }

    if (control?.hasError('blank')) {
      return 'Nome não deve ter espaço vazio.';
    }

    if (control?.hasError('maxlength')) {
      const maxLength = control.errors?.['maxlength'].requiredLength;

      return `Nome não deve ter mais de ${maxLength} caracteres.`;
    }

    return '';
  }

  private cleanError() {
    this.message = '';

    this.messageError = '';
  }
}

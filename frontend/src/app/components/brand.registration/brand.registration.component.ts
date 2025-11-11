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
import { isBlankValidator } from '../../../validators/blank.validator';
import { ValidationService } from '../../service/validation/validation.service';

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
        isBlankValidator()
      ]),
    }
  );
  message: string = '';
  messageError: string = '';

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

  getErrorMessage(controlName: string, label: string) {
    const control = this.form.get(controlName);
    return ValidationService.getErrorMessage(control, label)
  }

  private cleanError() {
    this.message = '';
    this.messageError = '';
  }
}

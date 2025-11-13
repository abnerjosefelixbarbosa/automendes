import { Component, inject } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import {
  FormGroup,
  FormControl,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ModelRequest, ModelService } from '../../service/model/model.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ValidationService } from '../../service/validation/validation.service';
import { blankValidator } from '../../validators/blank.validator';

@Component({
  selector: 'app-model.registration',
  imports: [NavbarComponent, ReactiveFormsModule],
  templateUrl: './model.registration.component.html',
  styleUrl: './model.registration.component.css',
})
export class ModelRegistrationComponent {
  form = new FormGroup({
    name: new FormControl('', [
      Validators.required,
      Validators.maxLength(30),
      blankValidator(),
    ]),
    bradName: new FormControl('', [
      Validators.required,
      Validators.maxLength(30),
      blankValidator(),
    ]),
  });
  message: string = '';
  messageError: string = '';
  private modelService = inject(ModelService);

  constructor() {}

  registerModel(data: FormGroup) {
    this.cleanError();

    const request: ModelRequest = {
      name: data.get('name')?.value,
      brandName: data.get('bradName')?.value,
    };

    this.modelService
      .registerModel(request)
      .then(() => {
        this.message = 'Modelo registrado.';
      })
      .catch((e: HttpErrorResponse) => {
        this.messageError = e.error.message;
      });
  }

  getErrorMessage(controlName: string, label: string) {
    const control = this.form.get(controlName);
    return ValidationService.getErrorMessage(control, label);
  }

  private cleanError() {
    this.messageError = '';
    this.message = '';
  }
}

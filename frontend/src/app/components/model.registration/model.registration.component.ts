import { Component, inject } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';
import { ModelRequest, ModelService } from '../../service/model/model.service';

@Component({
  selector: 'app-model.registration',
  imports: [NavbarComponent, ReactiveFormsModule],
  templateUrl: './model.registration.component.html',
  styleUrl: './model.registration.component.css',
})
export class ModelRegistrationComponent {
  form = new FormGroup({
    name: new FormControl('', []),
    modelName: new FormControl('', []),
  });
  formError = {
    name: '',
    modelName: '',
  };
  requestError = {
    message: '',
  };
  requestSuccess = {
    message: '',
  };
  private modelService = inject(ModelService);

  constructor() {}

  registerModel(data: FormGroup) {
    this.cleanError();

    const request: ModelRequest = {
      ...data.value,
    };

    try {
      this.modelService
        .registerModel(request)
        .then(() => {
          this.requestSuccess.message = 'Modelo registrado.';
        })
        .catch((e) => {
          const message = e.error.message;
          this.requestError.message = message;
          console.error(message)
        });
    } catch (e: any) {
      const message: string = e.message;
      if (message.includes('Nome')) {
        this.formError.name = message;
      }
      if (message.includes('Nome do modelo')) {
        this.formError.modelName = message;
      }
    }
  }

  private cleanError() {
    this.formError.name = '';
    this.formError.modelName = '';
    this.requestError.message = '';
    this.requestSuccess.message = '';
  }
}

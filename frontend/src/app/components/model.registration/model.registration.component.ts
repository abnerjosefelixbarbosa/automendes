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
    bradName: new FormControl('', []),
  });
  formError = {
    name: '',
    bradName: '',
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
      name: data.get('name')?.value,
      brandName: data.get('bradName')?.value
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
        });
    } catch (e: any) {
      const message: string = e.message;
      if (message.includes('Nome')) {
        this.formError.name = message;
      }
      if (message.includes('Nome da marca')) {
        this.formError.bradName = message;
      }

      console.log(message.search('Nome da marca'))
    }
  }

  private cleanError() {
    this.formError.name = '';
    this.formError.bradName = '';
    this.requestError.message = '';
    this.requestSuccess.message = '';
  }
}

import { Component, inject } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { BrandRequest, BrandService } from '../../service/brand/brand.service';
import { Brand } from '../../model/brand/brand';

@Component({
  selector: 'app-brand-registration',
  imports: [NavbarComponent, ReactiveFormsModule],
  templateUrl: './brand.registration.component.html',
  styleUrl: './brand.registration.component.css',
})
export class BrandRegistrationComponent {
  form = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.maxLength(30)]),
  });
  formError = {
    name: '',
  };
  requestError = {
    message: '',
  };
  requestSuccess = {
    message: '',
  };
  private brandService = inject(BrandService);

  constructor() {}

  registerBrand(data: FormGroup) {
    this.cleanError();

    const request: BrandRequest = {
      ...data.value,
    };

    try {
      this.brandService
        .registerBrand(request)
        .then(() => {
          this.requestSuccess.message = 'Marca registrada.';
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
    }
  }

  private cleanError() {
    this.formError.name = '';
    this.requestError.message = '';
    this.requestSuccess.message = '';
  }
}

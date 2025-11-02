import { Component, inject } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { BrandService } from '../../service/brand/brand.service';
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
  private brandService = inject(BrandService);

  constructor() {}

  registerBrand(data: FormGroup) {
    this.cleanForm();

    const brand = this.createBrand(data);

    try {
      this.brandService
        .registerBrand(brand)
        .then((value) => {
          console.log(value);
        })
        .catch((e) => {
          const message = e.error.message;

          console.error(message);

          if (message.includes('Nome')) {
            this.formError.name = message;
          }
        });
    } catch (e: any) {
      const message: string = e.message;

      console.error(message);

      if (message.includes('Nome')) {
        this.formError.name = message;
      }
    }
  }

  private cleanForm() {
    this.formError.name = '';
  }

  private createBrand(data: FormGroup) {
    const { name } = data.value;

    const brand: Brand = {
      id: null,
      name: name,
    };

    return brand;
  }
}

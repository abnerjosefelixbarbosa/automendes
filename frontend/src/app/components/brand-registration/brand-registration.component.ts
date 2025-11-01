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
  templateUrl: './brand-registration.component.html',
  styleUrl: './brand-registration.component.css',
})
export class BrandRegistrationComponent {
  form = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.maxLength(30)]),
  });
  private brandService = inject(BrandService);
  submitted = false;

  constructor() {}

  registerBrand(data: FormGroup) {
    this.submitted = true;

    const { name } = data.value;

    const brand: Brand = {
      id: null,
      name: name,
    };

    console.log(brand)

    try {
      this.brandService.registerBrand(brand);
    } catch (e: any) {
      console.error(e.message);
    }
  }
}

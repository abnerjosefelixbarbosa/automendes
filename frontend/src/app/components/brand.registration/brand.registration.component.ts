import { Component, inject } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { BrandRequest, BrandService } from '../../service/brand/brand.service';
import { ApplicationError } from '../../exceptions/application.error';

@Component({
  selector: 'app-brand-registration',
  imports: [NavbarComponent, ReactiveFormsModule],
  templateUrl: './brand.registration.component.html',
  styleUrl: './brand.registration.component.css',
})
export class BrandRegistrationComponent {
  form = new FormGroup({
    name: new FormControl('', []),
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

  async registerBrand(data: FormGroup) {
    this.cleanError();

    const request: BrandRequest = {
      name: data.get('name')?.value,
    };

    try {
      await this.brandService.registerBrand(request)

    } catch (e) {
      if (e instanceof ApplicationError) {
        if (e.message.includes('Nome não deve ser repetido.')) {
          this.requestError.message = e.message;
        } else {
          this.formError.name = e.message;
        }
      }
    }
  }

  private cleanError() {
    this.formError.name = '';
    this.requestError.message = '';
    this.requestSuccess.message = '';
  }
}

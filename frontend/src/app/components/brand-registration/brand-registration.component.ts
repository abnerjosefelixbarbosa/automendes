import { Component } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-brand-registration',
  imports: [NavbarComponent, ReactiveFormsModule],
  templateUrl: './brand-registration.component.html',
  styleUrl: './brand-registration.component.css'
})
export class BrandRegistrationComponent {
  brandRegistrationForm = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.maxLength(30)])
  });

  errorForm = {
    name: ''
  }

  registrationBrand(data: FormGroup) {
    //console.log(data.value)

    this.cleanError()

    this.validateForm(data)

    
    
    //console.log(data.get('name')?.errors)
  }

  private validateForm(data: FormGroup) {
    const { name } = data.value;

    if (name === '') {
      this.errorForm.name = 'Nome não deve ser vario.'
    }

    if (name.length > 30) {
      this.errorForm.name = 'Nome não deve ter mais de 30 caracteres.'
    }
  }

  private cleanError() {
    this.errorForm.name = ''
  }
}

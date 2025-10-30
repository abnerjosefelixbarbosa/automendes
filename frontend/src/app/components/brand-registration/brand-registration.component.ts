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

  registrationBrand(data: FormGroup) {
    console.log(data.value)
  }
}

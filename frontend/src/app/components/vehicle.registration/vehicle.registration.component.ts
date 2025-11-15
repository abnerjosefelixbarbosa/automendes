import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ValidationService } from '../../service/validation/validation.service';

@Component({
  selector: 'app-vehicle-registration',
  imports: [NavbarComponent, ReactiveFormsModule],
  templateUrl: './vehicle.registration.component.html',
  styleUrl: './vehicle.registration.component.css',
})
export class VehicleRegistrationComponent {
  form = new FormGroup({
    name: new FormControl('', []),
    bradName: new FormControl('', []),
  });
  message: string = '';
  messageError: string = '';

  registerModel(data: FormGroup) {
    this.cleanError();
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

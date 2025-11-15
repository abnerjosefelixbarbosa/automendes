import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { BrandRegistrationComponent } from './components/brand.registration/brand.registration.component';
import { ModelRegistrationComponent } from './components/model.registration/model.registration.component';
import { VehicleRegistrationComponent } from './components/vehicle.registration/vehicle.registration.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'brand-registration',
    component: BrandRegistrationComponent,
  },
  {
    path: 'model-registration',
    component: ModelRegistrationComponent,
  },
  {
    path: 'vehicle-registration',
    component: VehicleRegistrationComponent,
  },
];

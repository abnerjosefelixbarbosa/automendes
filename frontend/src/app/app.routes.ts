import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { BrandRegistrationComponent } from './components/brand.registration/brand.registration.component';

export const routes: Routes = [
    {
        path: "",
        component: HomeComponent
    },
    {
        path: "brand-registration",
        component: BrandRegistrationComponent
    }
];

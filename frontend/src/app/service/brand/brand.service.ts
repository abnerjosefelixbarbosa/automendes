import { Injectable } from '@angular/core';
import { Brand } from '../../model/brand/brand';
import { ApplicationError } from '../../exceptions/application-error';

@Injectable({
  providedIn: 'root',
})
export class BrandService {
  constructor() {}

  registerBrand(data: Brand) {
    
  }
}

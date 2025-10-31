import { Injectable } from '@angular/core';

interface Brand {
  name: string
}

@Injectable({
  providedIn: 'root'
})
export class BrandService {

  constructor() { }

  public registerBrand(data: Brand) {

  }

  private validateBrand(data: Brand) {

  }
}

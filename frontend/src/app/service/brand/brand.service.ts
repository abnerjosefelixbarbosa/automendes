import { Injectable } from '@angular/core';
import { Brand } from '../../model/brand/brand';
import { ApplicationError } from '../../exceptions/application.error';
import { HttpClient } from '@angular/common/http';
import { api } from '../../utils/api';
import { firstValueFrom } from 'rxjs';

interface BrandResponse {
  id: string;
  name: string;
}

@Injectable({
  providedIn: 'root',
})
export class BrandService {
  private url: string = api.development;

  constructor(private http: HttpClient) {}

  registerBrand(data: Brand) {
    this.validateBrand(data);

    const response = this.http.post<BrandResponse>(
      `${this.url}/brands/register-brand`,
      {
        name: data.name
      }
    );

    return firstValueFrom(response);
  }

  private validateBrand(data: Brand) {
    if (data.name === '') {
      throw new ApplicationError('Nome não deve ser vazio.')
    }
  }
}

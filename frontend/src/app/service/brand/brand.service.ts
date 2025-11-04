import { Injectable } from '@angular/core';
import { ApplicationError } from '../../exceptions/application.error';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface BrandRequest {
  name: string;
}

interface BrandResponse {
  id: string;
  name: string;
}

@Injectable({
  providedIn: 'root',
})
export class BrandService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  registerBrand(data: BrandRequest) {
    this.validateBrand(data);

    const response = this.http.post<BrandResponse>(
      `${this.apiUrl}/brands/register-brand`,
      data
    );

    return firstValueFrom(response);
  }

  private validateBrand(data: BrandRequest) {
    if (data.name === '') {
      throw new ApplicationError('Nome não deve ser vazio.');
    }

    if (data.name.length > 30) {
      throw new ApplicationError('Nome não deve ter mais de 30 caracteres.');
    }

    if (data.name.includes(' ')) {
      throw new ApplicationError('Nome não deve ter espaço vazio.');
    }
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { firstValueFrom } from 'rxjs';
import { ApplicationError } from '../../exceptions/application.error';

export interface ModelRequest {
  name: string;
  brandName: string;
}

interface ModelResponse {
  id: string;
  name: string;
  brandName: string;
}

@Injectable({
  providedIn: 'root',
})
export class ModelService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  registerModel(data: ModelRequest) {
    this.validateModel(data);

    return firstValueFrom(
      this.http.post<ModelResponse>(
        `${this.apiUrl}/models/register-model`,
        data
      )
    );
  }

  private validateModel(data: ModelRequest) {
    if (data.name === '') {
      throw new ApplicationError('Nome não deve ser vazio.');
    }

    if (data.name.length > 30) {
      throw new ApplicationError('Nome não deve ter mais de 30 caracteres.');
    }

    if (data.name.includes(' ')) {
      throw new ApplicationError('Nome não deve ter espaço vazio.');
    }

    if (data.brandName === '') {
      throw new ApplicationError('Nome da marca não deve ser vazio.');
    }

    if (data.brandName.length > 30) {
      throw new ApplicationError(
        'Nome da marca não deve ter mais de 30 caracteres.'
      );
    }

    if (data.brandName.includes(' ')) {
      throw new ApplicationError('Nome da marca não deve ter espaço vazio.');
    }
  }
}

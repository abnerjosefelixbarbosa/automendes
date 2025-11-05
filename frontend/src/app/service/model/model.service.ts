import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { BrandRequest } from '../brand/brand.service';
import { firstValueFrom } from 'rxjs';
import { ApplicationError } from '../../exceptions/application.error';

export interface ModelRequest {
  name: string;
  modelName: string;
}

interface ModelResponse {
  id: string;
  name: string;
  modelName: string;
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

    if (data.modelName === '') {
      throw new ApplicationError('Nome do modelo não deve ser vazio.');
    }

    if (data.modelName.length > 30) {
      throw new ApplicationError(
        'Nome do modelo não deve ter mais de 30 caracteres.'
      );
    }

    if (data.modelName.includes(' ')) {
      throw new ApplicationError('Nome do modelo não deve ter espaço vazio.');
    }
  }
}

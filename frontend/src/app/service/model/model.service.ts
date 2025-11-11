import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { firstValueFrom } from 'rxjs';

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
    return firstValueFrom(
      this.http.post<ModelResponse>(
        `${this.apiUrl}/models/register-model`,
        data
      )
    );
  }
}

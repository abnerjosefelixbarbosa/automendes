import { Injectable } from '@angular/core';
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

  registerBrand(data: BrandRequest): Promise<BrandResponse> {
    return firstValueFrom(
      this.http.post<BrandResponse>(
        `${this.apiUrl}/brands/register-brand`,
        data
      )
    );
  }
}

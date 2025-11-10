import { Injectable } from '@angular/core';
import { ApplicationError } from '../../exceptions/application.error';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, firstValueFrom, Observable, of, throwError } from 'rxjs';
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

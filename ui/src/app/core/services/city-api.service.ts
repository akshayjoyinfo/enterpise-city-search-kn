import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ApiPaginatedModel } from '../models/api-model';
import { City } from '../models/city.model';
import { Constants } from '../../../config/constatnts';
import { retry, catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { environment as env } from '../../../environments/environment';

@Injectable()
export class CityApiService {
  
  private API_HOST:string='';
  constructor(private http: HttpClient) {
    this.API_HOST = env.apiUrl;
  }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };
  

  public getCities(query:string="",page: number=1, size:number=10) {
    console.log('Called CityAPI Service');
    
    let CITIES_URL =`/cities?query=${query}&page=${page}&size=${size}`;
    return this.http
    .get<ApiPaginatedModel<City[]>>(this.API_HOST+CITIES_URL)
    .pipe(retry(2), catchError(this.handleError));
  }

  public getCity(url: string, options?: any) {
    return this.http.get(url, options);
  }

  public updateCity(data: City) {
    let CITIES_URL =`/cities/${data._id}`;
    console.log(this.API_HOST+CITIES_URL);
    return this.http.put(this.API_HOST+CITIES_URL, data)
    .pipe(retry(2), catchError(this.handleError));
  }


  handleError(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(error);
    
    return throwError(() => {
      return errorMessage;
    });
  }
}

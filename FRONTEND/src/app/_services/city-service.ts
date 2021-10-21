import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { CityDto } from '../_models/city-dto';
import { PageSizeResponse } from '../_models/page-size-response';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class CityService {

  constructor(private http: HttpClient, private authService: AuthenticationService) { }

  getCityList(pageNumber: number, pageSize: number) {
    if(pageSize <= 0){
      pageSize = 1;
    }
    if(pageNumber <= 0){
      pageNumber = 1;
    }

       return this.http.get<CityDto[]>(`${environment.apiUrl}/city/get/pages?page=${pageNumber}&size=${pageSize}`)
  
  }

  getCityByNameStartingWith(startsWith: string) {
    return  this.http.get<CityDto>(`${environment.apiUrl}/city/get/startsWith?name=${startsWith}`);
  }

  getPageNumberCount(pageSize: number) {

    return  this.http.get<PageSizeResponse>(`${environment.apiUrl}/city/get/pagesCount?size=${pageSize}`);
  }

  updateCity(dto: CityDto) {
    const httpOption = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
  };
      
    return this.http.post<CityDto>(`${environment.apiUrl}/city/update`, dto, httpOption);
  }

}

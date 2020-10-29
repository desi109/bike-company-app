import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class BikeCompanyService {

  constructor(private http:HttpClient) { }

  getBikes() {
    return this.http.get('/server/api/v1/bikes');
  }
}

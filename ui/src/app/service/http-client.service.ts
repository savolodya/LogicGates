import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(private httpClient: HttpClient) {}

  getResult() {
    return this.httpClient.get<boolean>('http://localhost:8090/');
  }
}

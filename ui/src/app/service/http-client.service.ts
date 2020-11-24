import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {
  apiUrl = "http://localhost:8090/";

  constructor(private httpClient: HttpClient) {}

  getResult(formula, parameters) {
    let query = 'formula='+formula.replace(/&/g, encodeURIComponent('&'));
    parameters.forEach((value, key) => query+='&'+key+'='+value);

    const opts = { params: new HttpParams({fromString: query}) };

    return this.httpClient.get<boolean>(this.apiUrl + "result", opts);
  }
}

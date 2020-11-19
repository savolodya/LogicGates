import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(private httpClient: HttpClient) {}

  getResult(formula, parameters) {
    let params = {
      formula: formula,
      parameters: JSON.stringify(Array.from(parameters.entries()))
    }

    console.log(JSON.stringify(params));

    return this.httpClient.get<boolean>('http://localhost:8090/result', {
      responseType:'json', params:params
    });
  }
}

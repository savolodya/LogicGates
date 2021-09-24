import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ResultData} from "../model/result-data";

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

  getTruthTable(formula: string, inputs: string[]) {
    let query = {
      formula: formula.replace(/&/g, encodeURIComponent('&')),
      inputs: inputs
    }

    let params = new HttpParams()
      .set('formula', formula.replace(/&/g, encodeURIComponent('&')))
      .set('inputs', encodeURIComponent(inputs.toString()));

    return this.httpClient.post<ResultData[]>(this.apiUrl + "result/truthTable", query);
  }
}

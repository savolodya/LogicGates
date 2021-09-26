import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ResultData} from "../model/result-data";
import {CalculatorDto} from "../dto/calculator-dto";

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {
  apiUrl = "http://localhost:8090/";
  calculatorDto: CalculatorDto;

  constructor(private httpClient: HttpClient) {}

  getResult(formula: string, parameters: Map <string, boolean>) {
    this.calculatorDto = {
      formula: formula,
      inputs: Array.from(parameters.keys()),
      values: Array.from(parameters.values())
    };

    return this.httpClient.post<boolean>(this.apiUrl + "result", this.calculatorDto);
  }

  getTruthTable(formula: string, inputs: string[]) {
    this.calculatorDto = {
      formula: formula,
      inputs: inputs,
      values: null
    };

    return this.httpClient.post<ResultData[]>(this.apiUrl + "result/truthTable", this.calculatorDto);
  }
}

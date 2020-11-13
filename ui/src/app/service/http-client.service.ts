import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

export class Vars {
  constructor(
    public name:string,
  ) {}
}

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(private httpClient:HttpClient) {}

  getVars() {
    return this.httpClient.get<Vars[]>('http://localhost:8090/')
  }
}

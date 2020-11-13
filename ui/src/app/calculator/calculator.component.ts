import { Component, OnInit } from '@angular/core';
import { HttpClientService } from "../service/http-client.service";

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.scss']
})
export class CalculatorComponent implements OnInit {

  vars:string[]

  constructor(private httpClientService:HttpClientService) { }

  ngOnInit(): void {
    this.httpClientService.getVars().subscribe(
      response => this.handleSuccessfulResponse(response),
    );
  }

  handleSuccessfulResponse(response) {
    this.vars = response;
  }

}

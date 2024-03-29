import { Component, OnInit } from '@angular/core';
import {HttpClientService} from "../service/http-client.service";
import {ResultData} from "../model/result-data";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {
  parameters: Map <string, boolean>;
  formula: string;
  result: boolean;
  isCalculated: boolean;
  isGenerated: boolean;
  truthTable: ResultData[];

  constructor(private httpClientService:HttpClientService) {  }

  ngOnInit(): void {
    this.parameters = new Map<string, boolean>();
    this.result = null;
    this.isCalculated = false;
    this.isGenerated = false;
    this.formula = "";
  }

  setInputs(event: any) {
    this.result = null;
    this.formula = event.target.value;
    let formula = this.formula
      .replace(/\s/g, "");
    let inputArr = formula.match(/(_*([a-zA-Z]+(_*[0-9])*_*)+)(\\^)?/g);

    this.parameters.clear();

    inputArr ? inputArr.forEach(input => this.parameters.set(input, true)) : {};
  }

  getKeys(map) {
    return Array.from(map.keys());
  }

  changeState(input: any) {
    this.parameters.set(input, !this.parameters.get(input));
  }

  calculate() {
    this.httpClientService.getResult(this.formula, this.parameters)
      .subscribe(
        response => {
          this.result = response;
          this.isCalculated = true;
        },
        error => console.log(error)
      );
  }

  back() {
    this.isCalculated = false;
    this.isGenerated = false;
    this.result = null;
  }

  generateTruthTable() {
    this.httpClientService.getTruthTable(this.formula, Array.from(this.parameters.keys()))
      .subscribe(
        response => {
          this.truthTable = response;
          this.isGenerated = true;
        },
        error => console.log(error)
      );
  }
}

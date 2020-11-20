import { Component, OnInit } from '@angular/core';
import {HttpClientService} from "../service/http-client.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {
  //TODO: check (compile)
  parameters: Map <string, boolean>;
  formula: string;
  heightLeftBar: number;

  constructor(private httpClientService:HttpClientService, private router: Router) {  }

  ngOnInit(): void {
    this.parameters = new Map<string, boolean>();
  }

  setInputs(event: any) {
    this.formula = event.target.value;
    let formula = this.formula
      .replace(/\s/g, "");
    let inputArr = formula.match(/(_*([a-zA-Z]+(_*[0-9])*_*)+)(\\^)?/g);

    this.parameters.clear();

    inputArr ? inputArr.forEach(input => this.parameters.set(input, true)) : {};
  }

  getKeys(map){
    return Array.from(map.keys());
  }

  changeState(input: any) {
    this.parameters.set(input, !this.parameters.get(input));
  }

  calculate() {
    let result;

    this.httpClientService.getResult(this.formula, this.parameters)
      .subscribe(
        response => result = response,
        error => console.log(error)
      );

    this.router.navigateByUrl('/result', {skipLocationChange: true});
  }
}

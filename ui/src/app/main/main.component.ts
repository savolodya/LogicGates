import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {
  //TODO: check (compile)
  inputs: Set <string>;
  qwe = ["1","2"];

  constructor() {
    this.inputs = new Set<string>();
  }

  ngOnInit(): void {
  }

  setInputs(event: any) {
    let formula = event.target.value
      .replace(/\s/g, "");
    let inputArr = formula.match(/(_*([a-zA-Z]+(_*[0-9])*_*)+)(\\^)?/g);

    this.inputs.clear();

    inputArr.forEach(input => this.inputs.add(input));
    console.log(this.inputs);
  }
}

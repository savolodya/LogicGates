import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {
  //TODO: check (compile)
  inputs: Map <string, boolean>;
  heightLeftBar: number;

  constructor() {
    this.inputs = new Map<string, boolean>();
  }

  ngOnInit(): void {
  }

  setInputs(event: any) {
    let formula = event.target.value
      .replace(/\s/g, "");
    let inputArr = formula.match(/(_*([a-zA-Z]+(_*[0-9])*_*)+)(\\^)?/g);

    this.inputs.clear();

    inputArr ? inputArr.forEach(input => this.inputs.set(input, true)) : {};
  }

  getKeys(map){
    return Array.from(map.keys());
  }

  changeState(input: any) {
    this.inputs.set(input, !this.inputs.get(input));
  }
}

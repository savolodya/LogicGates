import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {
  //TODO: check (compile)
  inputs: Set<string> = new Set<string>();

  constructor() { }

  ngOnInit(): void {
  }

  setInputs(event: any) {
    //TODO: a\v|a\s|b|aa !-> Set(4) {"v", "s", "b", "aa"}
    let formula = event.target.value;
    let i = 0;
    let input: string;

    formula = formula.replace(/\s/g, "");
    input = "";
    this.inputs.clear();

    formula.split("").forEach(c => {
      if (this.isLetter(c)) {
        if (input == "")
          this.inputs.add(c);
        else {
          this.inputs.delete(input);
          this.inputs.add(input+c);
        }
        input += c;
      } else {
        i++;
        input = "";
        this.inputs[i] = "";
      }
    });

    console.log(this.inputs);
    console.log(this.inputs.size);
  }

  isLetter(value: String) {
    //TODO: variable check (q1, qqq, _q), not (1q, 1..)
    return (value >= 'a' &&  value <= 'z') || (value >= 'A' &&  value <= 'Z');
  }
}

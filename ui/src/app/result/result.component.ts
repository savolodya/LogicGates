import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.scss']
})
export class ResultComponent implements OnInit {
  parameters: Map <string, boolean>;
  formula: string;
  result: boolean;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(
      (params:any) => {
        this.formula = params['formula'];
        this.parameters = params['parameters'];
        this.result = params['result'];
      }
    );
  }

  getKeys(map){
    return Array.from(map.keys());
  }

}

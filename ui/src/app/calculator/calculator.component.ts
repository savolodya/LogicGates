import {Component, OnDestroy, OnInit} from '@angular/core';
import { HttpClientService } from "../service/http-client.service";

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.scss']
})
export class CalculatorComponent implements OnInit, OnDestroy {
  result:boolean
  private subscription;

  constructor(private httpClientService:HttpClientService) { }

  ngOnInit(): void {
    this.subscription = this.httpClientService.getResult()
      .subscribe(
      response => this.result = response,
      error => console.log(error)
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}

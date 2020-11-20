import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MainComponent } from "./main/main.component";
import { HelpComponent } from "./help/help.component";
import {ResultComponent} from "./result/result.component";

const routes: Routes = [
  {path: '', component: MainComponent},
  {path: 'help', component: HelpComponent},
  {path: 'result', component: ResultComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

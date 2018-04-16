import {UserListComponent} from './user-list/user-list.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { LangListComponent } from './lang-list/lang-list.component';
import { LangDetailsComponent } from './lang-details/lang-details.component';

const routes: Routes = [
  { path: '', redirectTo: '/lang', pathMatch: 'full' },
  { path: 'lang', component: LangListComponent },
  { path: 'lang/:id', component: LangDetailsComponent },
  { path: 'user', component: UserListComponent },
  ];

@NgModule({
  exports: [
     RouterModule ],
     imports: [ RouterModule.forRoot(routes) ],
  declarations: []
})
export class AppRoutingModule { }

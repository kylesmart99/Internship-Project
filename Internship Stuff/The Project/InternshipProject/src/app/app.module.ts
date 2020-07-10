import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { PasswordInputComponent } from './password-input/password-input.component';
import { RouterModule } from '@angular/router'; 

@NgModule({
  declarations: [
    AppComponent,
    PasswordInputComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot([
      { path: 'home', component: PasswordInputComponent },
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: '**', redirectTo: 'home', pathMatch: 'full' }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

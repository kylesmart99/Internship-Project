import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ButtonPressingComponent } from './button-pressing/button-pressing.component';
import { AppComponent } from './app.component';
import { PasswordInputComponent } from './password-input/password-input.component';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    PasswordInputComponent,
    ButtonPressingComponent
  ],
  imports: [
  BrowserModule,
  RouterModule.forRoot([
    { path: 'home', component: PasswordInputComponent },
    { path: 'buttons', component: ButtonPressingComponent },
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: '**', redirectTo: 'home', pathMatch: 'full' }
  ]),
  FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

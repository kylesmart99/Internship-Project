import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-password-input',
  templateUrl: './password-input.component.html',
  styleUrls: ['./password-input.component.css']
})
export class PasswordInputComponent implements OnInit {
  pageTitle = 'Welcome to the super secure password login for {{user.bankName}}.';
  constructor() { }

  hasEnteredValidPassword = false;

  ngOnInit(): void {
  }

  enterPassword(isPasswordValid: boolean): void {
    this.hasEnteredValidPassword = isPasswordValid;
  }
}

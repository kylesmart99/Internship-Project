import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-password-input',
  templateUrl: './password-input.component.html',
  styleUrls: ['./password-input.component.css']
})
export class PasswordInputComponent implements OnInit {
  pageTitle: string = 'Welcome to the super secure password login for {{user.bankName}}.';
  
  constructor() { }

  ngOnInit(): void {
  }

}

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ButtonPressingComponent } from './button-pressing.component';

describe('ButtonPressingComponent', () => {
  let component: ButtonPressingComponent;
  let fixture: ComponentFixture<ButtonPressingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ButtonPressingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ButtonPressingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

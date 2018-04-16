import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LangDetailsComponent } from './lang-details.component';

describe('LangDetailsComponent', () => {
  let component: LangDetailsComponent;
  let fixture: ComponentFixture<LangDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LangDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LangDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentDialogComponent } from './student-dialog.component';

describe('StudentDialogComponent', () => {
  let component: StudentDialogComponent;
  let fixture: ComponentFixture<StudentDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudentDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

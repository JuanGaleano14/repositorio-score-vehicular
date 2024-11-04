import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScoreCheckerComponent } from './score-checker.component';

describe('ScoreCheckerComponent', () => {
  let component: ScoreCheckerComponent;
  let fixture: ComponentFixture<ScoreCheckerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ScoreCheckerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScoreCheckerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

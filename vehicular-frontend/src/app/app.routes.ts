import { Routes } from '@angular/router';
import { ScoreCheckerComponent } from './features/score-checker/score-checker.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/score-checker',
    pathMatch: 'full'
  },
  {
    path: 'score-checker',
    component: ScoreCheckerComponent
  }
];

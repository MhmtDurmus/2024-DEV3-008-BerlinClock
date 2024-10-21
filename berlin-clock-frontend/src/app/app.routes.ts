import { Routes } from '@angular/router';
import {BerlinClockComponent} from './berlin-clock/berlin-clock.component';

export const routes: Routes = [
  { path: '', component: BerlinClockComponent },
  { path: 'clock', component: BerlinClockComponent }
];

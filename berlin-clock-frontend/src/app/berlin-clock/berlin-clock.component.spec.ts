import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BerlinClockComponent} from './berlin-clock.component';
import {By} from '@angular/platform-browser';

describe('BerlinClockComponent', () => {
  let component: BerlinClockComponent;
  let fixture: ComponentFixture<BerlinClockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BerlinClockComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BerlinClockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should display the correct five-hour lamp states', () => {
    component.timeData = {fiveHoursLamp: 'RRYO'};
    fixture.detectChanges();

    const fiveHourLamps = fixture.debugElement.queryAll(By.css('.hours-lamps div'));

    expect(fiveHourLamps.length).toBe(4);
    expect(fiveHourLamps[0].classes['red']).toBeTrue();
    expect(fiveHourLamps[1].classes['red']).toBeTrue();
    expect(fiveHourLamps[2].classes['yellow']).toBeTrue();
    expect(fiveHourLamps[3].classes['off']).toBeTrue();
  });
});

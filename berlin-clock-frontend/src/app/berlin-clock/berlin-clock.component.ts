import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {WebsocketService} from '../websocket/websocket.service';


@Component({
  selector: 'app-berlin-clock',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './berlin-clock.component.html',
  styleUrl: './berlin-clock.component.scss'
})
export class BerlinClockComponent {

  timeData: any;
  currentTime: any;

  constructor(private websocketService: WebsocketService) {
    this.websocketService.getBerlinClockUpdates().subscribe((data) => {
      this.timeData = data
      this.currentTime = this.getCurrentDigitalTime()
    });
  }

  getCurrentDigitalTime(): string {
    const now = new Date();
    const hours = now.getHours().toString().padStart(2, '0');
    const minutes = now.getMinutes().toString().padStart(2, '0');
    return `${hours}:${minutes}`;
  }

  getLamps(lamps: string, colorCodes: string, total: number) {
    const lampClasses = [];
    for (let i = 0; i < total; i++) {
      lampClasses.push(i < lamps.length && lamps[i] === colorCodes[0] ? 'yellow' :
        (i < lamps.length && lamps[i] === colorCodes[1]) ? 'red' : 'off');
    }
    return lampClasses;
  }
}

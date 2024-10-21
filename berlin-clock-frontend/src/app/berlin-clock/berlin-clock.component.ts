import {Component} from '@angular/core';
import {WebsocketService} from '../websocket/websocket.service';


@Component({
  selector: 'app-berlin-clock',
  standalone: true,
  imports: [],
  templateUrl: './berlin-clock.component.html',
  styleUrl: './berlin-clock.component.scss'
})
export class BerlinClockComponent {

  timeData: any;
  currentTime: any;

  constructor(private websocketService: WebsocketService) {
    this.websocketService.getBerlinClockUpdates().subscribe((data) => {
      console.log(data)
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
}

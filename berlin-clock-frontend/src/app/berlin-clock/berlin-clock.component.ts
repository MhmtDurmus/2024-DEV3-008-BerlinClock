import {Component, OnInit} from '@angular/core';
import { WebsocketService } from '../websocket/websocket.service';


@Component({
  selector: 'app-berlin-clock',
  standalone: true,
  imports: [],
  templateUrl: './berlin-clock.component.html',
  styleUrl: './berlin-clock.component.scss'
})
export class BerlinClockComponent {

  timeData: any;

  constructor(private websocketService: WebsocketService) {
    this.websocketService.getBerlinClockUpdates().subscribe((data) => {
      this.timeData = data
    });
  }
}

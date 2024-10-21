import { Injectable } from '@angular/core';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class WebsocketService {
  private stompClient: Client = new Client();
  private berlinClockSubject = new Subject<any>();

  constructor() {
    this.connect();
  }

  private connect() {
    const url = 'http://localhost:8080/ws';
    const socket = new SockJS(url);

    this.stompClient = new Client({
      webSocketFactory: () => socket,
      onConnect: (frame) => {
        console.log('Connected: ' + frame);
        this.stompClient.subscribe('/topic/clock', (message) => {
          if (message.body) {
            console.log(message)
            this.berlinClockSubject.next(JSON.parse(message.body));
          }
        });
      },
      onStompError: (frame) => {
        console.error('Broker error: ' + frame.headers['message']);
        console.error('Additional details: ' + frame.body);
      },
    });

    this.stompClient.activate();
  }

  public getBerlinClockUpdates(): Observable<any> {
    return this.berlinClockSubject.asObservable();
  }
}

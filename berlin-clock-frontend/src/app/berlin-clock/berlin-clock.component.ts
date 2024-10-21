import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-berlin-clock',
  standalone: true,
  imports: [],
  templateUrl: './berlin-clock.component.html',
  styleUrl: './berlin-clock.component.scss'
})
export class BerlinClockComponent {

  timeData: any;  // Variable to hold time data

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getBerlinClockTime();
  }

  getBerlinClockTime(): void {
    this.http.get('http://localhost:8080/api/berlinclock')  // Call your backend API
      .subscribe((data) => {
        this.timeData = data;
      });
  }}

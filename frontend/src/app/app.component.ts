import { Component } from '@angular/core';
import { fadeAnimation } from './animations';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  animations: [fadeAnimation]
})
export class AppComponent {
  title = 'frontend';

  openSwagger() {
    window.open('http://localhost:8765/swagger-ui.html');
  }

  openEureka() {
    window.open('http://localhost:8761/');
  }
}
import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { UserComponent } from './user/user';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ButtonModule, UserComponent],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('front');
}

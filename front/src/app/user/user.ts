import { Component, signal } from '@angular/core';
import { Table, TableModule } from 'primeng/table';
import {UserService} from '../services/user.service';
import {UserAddComponent} from './user-add';
import { DialogModule } from 'primeng/dialog';
import {ButtonModule} from 'primeng/button';
import { FormsModule } from '@angular/forms'; // <--- AJOUTE CET IMPORT
import {User} from '../services/user.service';
import { RouterLink } from '@angular/router'; // <--- L'import est ici

@Component({
  selector: 'app-user',
  imports: [TableModule, UserAddComponent, FormsModule, DialogModule, ButtonModule,
    RouterLink],
  templateUrl: './user.html',
  styleUrl: './user.css',
  standalone: true,
  providers: [UserService]
})
export class UserComponent {
  datas = signal<User[]>([]);
  visible = signal(false);
  constructor(private userService: UserService) {
  }

ngOnInit() {
     this.loadUsers();
}

loadUsers() {
  this.userService.getAllUsers().subscribe({
        next: (data) => {
                this.datas.set(data);
                console.log("Données chargées :", this.datas());
              },
              error: (err) => console.error("Erreur lors du chargement", err)
   });
  }


  showDialog() {
    this.visible.set(true);
  }

onUserAdded(event: any) {
 this.visible.set(false); // 1. Ferme le dialogue PrimeNG
  this.loadUsers();        // 2. Relance le GET vers le backend
  }



}

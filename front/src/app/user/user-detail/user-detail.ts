import { Component, Input, OnInit, inject, signal } from '@angular/core';
import { User, UserService } from '../../services/user.service';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-user-detail',
  imports: [ButtonModule],
  templateUrl: './user-detail.html',
  styleUrl: './user-detail.css',
  standalone: true
})
export class UserDetail implements OnInit{
  @Input() id?: string;
  user = signal<User | null>(null);
  userService = inject(UserService);

  constructor() { }


  ngOnInit() {
      // Tu peux maintenant utiliser this.id pour appeler ton service Java
      console.log("L'ID est :", this.id);
      if(this.id) {

      this.userService.getUser(this.id).subscribe( (data: User) => {
        console.log(data);
        this.user.set(data);
      });
     }else {
       console.warn("Aucun ID n'a été fourni au composant UserDetail");
       }
    }

  createTask() {
    alert('ici on va creer un task');
  }

  }

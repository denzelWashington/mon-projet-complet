import { Component, Input, OnInit, inject, signal } from '@angular/core';
import { User, UserService } from '../../services/user.service';
import { ButtonModule } from 'primeng/button';
import { DialogService, DynamicDialogRef } from 'primeng/dynamicdialog';
import { CreateTask } from '../../tasks/create-task/create-task';
import { TaskService, Task} from '../../services/task.service';
@Component({
  selector: 'app-user-detail',
  imports: [ButtonModule],
  templateUrl: './user-detail.html',
  styleUrl: './user-detail.css',
  standalone: true,
  providers: [DialogService]
})
export class UserDetail implements OnInit{
  @Input() id?: string;
  user = signal<User | null>(null);
  userService = inject(UserService);
  taskService = inject(TaskService);

  tasks = signal<Task[]>([]);


  ref: DynamicDialogRef | null = null;

  constructor(public dialogService: DialogService) { }


  ngOnInit() {
      // Tu peux maintenant utiliser this.id pour appeler ton service Java
      console.log("L'ID est :", this.id);
      if(this.id) {

      this.userService.getUser(this.id).subscribe( (data: User) => {
        console.log(data);
        this.user.set(data);
      });



             this.taskService.getTask(this.id).subscribe(data => {
                                this.tasks.set(data);
                     });


     }else {
       console.warn("Aucun ID n'a été fourni au composant UserDetail");
       }






    }

  createTask() {
    this.ref = this.dialogService.open(CreateTask, {
        header: 'Creation du Task',
        data: { userId: this.id }
      });


    this.ref?.onClose.subscribe((task) => {
          if (task && this.id) {

             this.taskService.getTask(this.id).subscribe(data => {
                                            this.tasks.set(data);
                                 });

             }

        });



  }

  }

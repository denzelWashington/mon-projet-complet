import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';
import { Component, inject, signal } from '@angular/core';
import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/dynamicdialog';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import {Task, TaskService} from '../../services/task.service';

@Component({
  selector: 'app-create-task',
  imports: [ButtonModule, InputTextModule, SelectModule, ReactiveFormsModule], // ✅ ReactiveFormsModule pas FormControl
  templateUrl: './create-task.html',
})
export class CreateTask {
  visible = signal(false);
  private ref = inject(DynamicDialogRef); // ✅ inject() au lieu de constructor
  private config = inject(DynamicDialogConfig);
  private taskService = inject(TaskService);


  constructor() {

    }

  userId = this.config.data.userId; // ✅
  task? : Task;



  form = new FormGroup({
    title: new FormControl('', Validators.required),
    status: new FormControl(null, Validators.required),
  });

    statusOptions = [
    { label: 'To do', value: 'TODO' },
    { label: 'In progress', value: 'IN_PROGRESS' },
    { label: 'Done', value: 'DONE' },
    ];

    save() {
    if (this.form.invalid) return;
    this.task = {
           title:  this.form.get('title')?.value  ?? '',
               status: this.form.get('status')?.value ?? '',
        };


      this.taskService.saveTask(this.task,this.userId).subscribe(data => {
                   console.log(data);
        });
      this.ref.close(this.task); // ✅ déplace le close après avoir construit le task
    }

    cancel() { this.ref.close(); }

}

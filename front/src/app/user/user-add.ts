import { Component, EventEmitter, Output, signal } from '@angular/core';
import {UserService, User} from '../services/user.service';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import {FormGroup, FormControl, ReactiveFormsModule, Validators} from '@angular/forms';

@Component({
  selector: 'app-add-user',
  imports: [ButtonModule, DialogModule, InputTextModule, ReactiveFormsModule],
  template: `
  <div class="card flex justify-content-center" [formGroup]="userForm">
      <div class="flex flex-column gap-3 w-full">

          <div class="flex flex-column gap-2">
              <label for="firstName" class="font-semibold">Prénom</label>
              <input pInputText id="firstName" formControlName="firstName" />
          </div>

          <div class="flex flex-column gap-2">
              <label for="lastName" class="font-semibold">Nom</label>
              <input pInputText id="lastName" formControlName="lastName" />
          </div>

          <div class="flex flex-column gap-2">
              <label for="quota" class="font-semibold">Quota</label>
              <input pInputText id="quota" type="number" formControlName="quota" />
          </div>

          <div class="flex justify-content-end mt-4">
              <p-button label="Sauvegarder" icon="pi pi-check" [loading]="loading()" (onClick)="onSave()"></p-button>
          </div>

      </div>
  </div>
  `,
  standalone: true,
  providers: [UserService]
})
export class UserAddComponent {
  visible: boolean = false;
  loading = signal(false);
  @Output() finished = new EventEmitter();

    userForm = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      quota: new FormControl(0)
    });

  constructor(private userService: UserService) {}

  showDialog() {
    this.visible = true;
  }

onSave() {
    if (this.userForm.valid) {
      this.loading.set(true);

      const userData = this.userForm.getRawValue() as User;
        this.userService.saveUser(userData).subscribe({
         next: (res: any) => { // Vérifie l'ouverture de l'accolade
               this.userForm.reset();
               this.finished.emit();
             }, // <-- Vérifie la virgule ici entre next et error
             error: (err: any) => {
               console.error(err);
             }
      });

  }
}
}

import { Routes } from '@angular/router';
import { UserComponent } from './user/user';
import { UserDetail } from './user/user-detail/user-detail';
export const routes: Routes = [
{
    path: 'users',
    component: UserComponent,
    title: 'Gestion des Utilisateurs'
  },
  {
    path: 'user-detail/:id',
    component: UserDetail,
    title: 'Detail de l\' utilisateur'
  },
  {
    path: '**',
    redirectTo: 'users'
  }

  ];

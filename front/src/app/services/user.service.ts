import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface User {
    id?: string;
    firstName: string;
    lastName: string;
    quota: number;
 }
@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly apiUrl = "http://localhost:8082/api";
  private http = inject(HttpClient);

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/users`);
  }

  saveUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/user`, user);
  }

  getUser(id: string): Observable<User> {
        return this.http.get<User>(`${this.apiUrl}/user/${id}`);
   }

}

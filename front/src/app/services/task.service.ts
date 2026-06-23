import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Task {
    id?: string;
    title: string;
    status: string;
 }

@Injectable({
  providedIn: 'root'
})
export class TaskService {

    private readonly apiUrl = "http://localhost:9000/api";
    private http = inject(HttpClient);

   saveTask(task: Task, idUser: string) {
        return this.http.post<Task>(`${this.apiUrl}/task/${idUser}`, task); // ✅ ${} pas {$}
    }

    getTask(idUser: string) {
      return this.http.get<Task[]>(`${this.apiUrl}/task/${idUser}`); // ✅ ${} pas {$}
        }


}

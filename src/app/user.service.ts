import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './user';
import { Observable} from 'rxjs/Observable';

@Injectable()
export class UserService {
  apiUrl: string;
  constructor(private http: HttpClient) {this.apiUrl = 'http://localhost:4567'; }
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl + '/users');
    }
    createUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiUrl + '/users', user, {
    headers: new HttpHeaders().set('Content-Type', 'application/json')
    });

}}

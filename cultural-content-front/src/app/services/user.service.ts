import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
      private http: HttpClient
  ) { }


    public getUserSubs(id: number): Observable<any> {
        const authHeader = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('user'));
        authHeader.append('Content-Type', 'application/json');
        return this.http.get('api/api/users/' + id + '/subscriptions', {headers: authHeader, responseType: 'json'});
    }

    public unsubscribe(id: number): Observable<any> {
        const authHeader = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('user'));
        authHeader.append('Content-Type', 'application/json');
        return this.http.get('api/api/users/unsubscribe/' + id, {headers: authHeader, responseType: 'json'});
    }

    public subscribe(id: number): Observable<any> {
        const authHeader = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('user'));
        authHeader.append('Content-Type', 'application/json');
        return this.http.get('api/api/users/subscribe/' + id, {headers: authHeader, responseType: 'json'});
    }

    public changePassword(passwordDTO: object): Observable<any> {
        const authHeader = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('user'));
        authHeader.append('Content-Type', 'application/json');
        return this.http.post('api/auth/change-password', passwordDTO, {headers: authHeader, responseType: 'json'});
    }
}

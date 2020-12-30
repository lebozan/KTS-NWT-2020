import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
    private headers = new HttpHeaders({'Content-Type': 'application/json'});

    constructor(
    private http: HttpClient
    ) { }

    login(auth: any): Observable<any> {
        console.log(auth);
        return this.http.post('api/auth/login', {email: auth.email, password: auth.password},
    {headers: this.headers, responseType: 'json'});
    }

    logout(): Observable<any> {
        return this.http.get('api/auth/logout', {headers: this.headers, responseType: 'text'});
    }

    register(auth: any): Observable<any> {
        return this.http.post('api/auth/register',
            {firstName: auth.firstName, lastName: auth.lastName, email: auth.email, password: auth.password},
            {headers: this.headers, responseType: 'json'});
    }

    isLoggedIn(): boolean {
        console.log(localStorage.getItem('user'));
        if (!localStorage.getItem('user')) {
            return false;
        }
        return true;
    }
}

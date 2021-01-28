import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RatingService {

    constructor(
        private http: HttpClient
    ) { }

    public submitRating(rating: object): Observable<any> {
        const authHeader = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('user'));
        authHeader.append('Content-Type', 'application/json');
        return this.http.post('api/api/ratings', rating, {headers: authHeader, responseType: 'json'});
    }
}

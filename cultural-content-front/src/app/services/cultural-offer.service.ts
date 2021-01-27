import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CulturalOfferService {

    constructor(
        private http: HttpClient
    ) {}

    public getCulturalOffers(): Observable<any> {
        const authHeader = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('user'));
        authHeader.append('Content-Type', 'application/json');
        return this.http.get('api/api/cultural-offers', {headers: authHeader, responseType: 'json'});
    }

    public getCulturalOffer(offerId: number): Observable<any> {
        const authHeader = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('user'));
        authHeader.append('Content-Type', 'application/json');
        return this.http.get('api/api/cultural-offers/' + offerId, {headers: authHeader, responseType: 'json'});
    }

}

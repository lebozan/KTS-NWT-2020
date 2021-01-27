import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CulturalOfferSubtypeService {

  constructor(
      private http: HttpClient
  ) { }

    public getAllSubtypes(): Observable<any> {
        const authHeader = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('user'));
        authHeader.append('Content-Type', 'application/json');
        return this.http.get('api/api/cultural-offer-subtypes/list', {headers: authHeader, responseType: 'json'});
    }

    public deleteSubtype(id: number): Observable<any> {
        const authHeader = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('user'));
        authHeader.append('Content-Type', 'application/json');
        return this.http.delete('api/api/cultural-offer-subtypes/' + id, {headers: authHeader, responseType: 'json'});
    }

    public addNewSubtype(newSubtype: object): Observable<any> {
        const authHeader = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('user'));
        authHeader.append('Content-Type', 'application/json');
        return this.http.post('api/api/cultural-offer-subtypes', newSubtype, {headers: authHeader, responseType: 'json'});
    }

    public changeSubtypeName(subtypeId: number, newSubtypeName: string): Observable<any> {
        const authHeader = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('user'));
        authHeader.append('Content-Type', 'application/json');
        return this.http.put('api/api/cultural-offer-subtypes/' + subtypeId, {name: newSubtypeName}, {headers: authHeader, responseType: 'json'});
    }
}

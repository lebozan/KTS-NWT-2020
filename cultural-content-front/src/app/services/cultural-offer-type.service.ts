import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CulturalOfferTypeService {

    constructor(
        private http: HttpClient
    ) { }

    public getAllTypes(): Observable<any> {
        const authHeader = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('user'));
        authHeader.append('Content-Type', 'application/json');
        return this.http.get('api/api/cultural-offer-types/list', {headers: authHeader, responseType: 'json'});
    }

    public deleteType(id: number): Observable<any> {
        const authHeader = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('user'));
        authHeader.append('Content-Type', 'application/json');
        return this.http.delete('api/api/cultural-offer-types/' + id, {headers: authHeader, responseType: 'json'});
    }

    public addNewType(newType: object): Observable<any> {
        const authHeader = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('user'));
        authHeader.append('Content-Type', 'application/json');
        return this.http.post('api/api/cultural-offer-types/', newType, {headers: authHeader, responseType: 'json'});
    }

    public changeTypeName(subtypeId: number, newTypeName: string): Observable<any> {
        const authHeader = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('user'));
        authHeader.append('Content-Type', 'application/json');
        return this.http.put('api/api/cultural-offer-types/' + subtypeId, {name: newTypeName}, {headers: authHeader, responseType: 'json'});
    }
}

import { Injectable } from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AuthenticationService} from '../services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class LoginGuard implements CanActivate {

    constructor(
        public auth: AuthenticationService,
        public router: Router
    ) { }


    canActivate(): boolean {
        if (this.auth.isLoggedIn()) {
            this.router.navigate(['/user/details']);
            return false;
        }
        return true;
    }
}

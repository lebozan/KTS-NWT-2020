import { Component, OnInit } from '@angular/core';
import {JwtHelperService} from '@auth0/angular-jwt';

@Component({
  selector: 'app-navbar-user',
  templateUrl: './navbar-user.component.html',
  styleUrls: ['./navbar-user.component.scss']
})
export class NavbarUserComponent implements OnInit {
    currentUser: any;

  constructor() {
      this.currentUser = {};
  }

  ngOnInit(): void {
      const token = localStorage.getItem('user');
      const jwt: JwtHelperService = new JwtHelperService();
      if (!token) {
          return;
      }
      const info = jwt.decodeToken(token);
      this.currentUser = info;
  }

}

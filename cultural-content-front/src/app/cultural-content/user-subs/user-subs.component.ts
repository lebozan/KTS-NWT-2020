import {ApplicationRef, Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user.service';
import {JwtHelperService} from '@auth0/angular-jwt';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-subs',
  templateUrl: './user-subs.component.html',
  styleUrls: ['./user-subs.component.scss']
})
export class UserSubsComponent implements OnInit {
    userSubs: Array<any>;

  constructor(
      private userService: UserService
  ) {
      this.userSubs = [];
  }

  ngOnInit(): void {
      const token = localStorage.getItem('user');
      const jwt: JwtHelperService = new JwtHelperService();
      if (!token) {
          return;
      }
      const info = jwt.decodeToken(token);
      this.userService.getUserSubs(info.id).subscribe(
          result => {
              this.userSubs = result.subscriptions;
          }, error => {
              console.log(error);
          }
      );
  }
  unsubscribe(id: number): void {
      this.userService.unsubscribe(id).subscribe(
          result => {
              this.ngOnInit();
          }, error => {
              console.log(error.error);
          }
      );
  }

}

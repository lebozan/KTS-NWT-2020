import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {LoginGuard} from './guards/login.guard';
import {RegisterComponent} from './register/register.component';
import {UserPageComponent} from './cultural-content/user-page/user-page.component';
import {UserSubsComponent} from './cultural-content/user-subs/user-subs.component';
import {RoleGuard} from './guards/role.guard';

const routes: Routes = [{
  path: 'login', component : LoginComponent, canActivate: [LoginGuard]
}, {
  path: 'register', component : RegisterComponent, canActivate: [LoginGuard]
}, {
    path: 'user',
    children : [
        {
            path: 'details', component : UserPageComponent, canActivate: [RoleGuard],
            data: {expectedRoles: 'ROLE_USER'}
        }, {
            path: 'subs', component : UserSubsComponent, canActivate: [RoleGuard],
            data: {expectedRoles: 'ROLE_USER'}
        }
    ]
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

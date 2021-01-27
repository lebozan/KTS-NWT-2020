import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {LoginGuard} from './guards/login.guard';
import {RegisterComponent} from './register/register.component';
import {UserPageComponent} from './cultural-content/user-page/user-page.component';
import {UserSubsComponent} from './cultural-content/user-subs/user-subs.component';
import {RoleGuard} from './guards/role.guard';
import {CulturalOfferTypesComponent} from './cultural-content/cultural-offer-types/cultural-offer-types.component';
import {CulturalOfferSubtypesComponent} from './cultural-content/cultural-offer-subtypes/cultural-offer-subtypes.component';
import {HomeUserComponent} from './core/home-user/home-user.component';
import {CulturalOfferDetailsComponent} from './cultural-content/cultural-offer-details/cultural-offer-details.component';

const routes: Routes = [
    {
        path: '', component : HomeUserComponent, canActivate: [RoleGuard], data: {expectedRoles: 'ROLE_USER|ROLE_ADMIN'}
    },
    {
        path: 'login', component : LoginComponent, canActivate: [LoginGuard]
    }, {
        path: 'register', component : RegisterComponent, canActivate: [LoginGuard]
    }, {
        path: 'user',
        children : [
        {
            path: 'details', component : UserPageComponent, canActivate: [RoleGuard],
            data: {expectedRoles: 'ROLE_USER|ROLE_ADMIN'}
        }, {
            path: 'subs', component : UserSubsComponent, canActivate: [RoleGuard],
            data: {expectedRoles: 'ROLE_USER'}
        }
        ]
    },
    {
        path: 'cultural-offer-types', component: CulturalOfferTypesComponent, canActivate: [RoleGuard],
        data: {expectedRoles: 'ROLE_ADMIN'}
    },
    {
        path: 'cultural-offer-subtypes', component: CulturalOfferSubtypesComponent, canActivate: [RoleGuard],
        data: {expectedRoles: 'ROLE_ADMIN'}
    },
    {
        path: 'cultural-offer-details', component: CulturalOfferDetailsComponent, canActivate: [RoleGuard],
        data: {expectedRoles: 'ROLE_USER|ROLE_ADMIN'}
    }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

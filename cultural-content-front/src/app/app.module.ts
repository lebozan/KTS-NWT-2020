import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import {ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {TokenInterceptor} from './interceptors/token.interceptor';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { RegisterComponent } from './register/register.component';
import {ToastrModule} from 'ngx-toastr';
import {MaterialModule} from './material/material.module';
import { UserPageComponent } from './cultural-content/user-page/user-page.component';
import { ToolbarUserComponent } from './core/toolbar-user/toolbar-user.component';
import { NavbarUserComponent } from './core/navbar-user/navbar-user.component';
import { UserSubsComponent } from './cultural-content/user-subs/user-subs.component';
import { ChangePasswordDialogComponent } from './cultural-content/change-password-dialog/change-password-dialog.component';

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        RegisterComponent,
        UserPageComponent,
        ToolbarUserComponent,
        NavbarUserComponent,
        UserSubsComponent,
        ChangePasswordDialogComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        ReactiveFormsModule,
        MatInputModule,
        HttpClientModule,
        ToastrModule.forRoot(),
        MaterialModule
    ],
    entryComponents: [ChangePasswordDialogComponent],
    providers: [{provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true}],
    bootstrap: [AppComponent]
})
export class AppModule { }

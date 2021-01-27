import { BrowserModule } from '@angular/platform-browser';
import {EventEmitter, NgModule} from '@angular/core';

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
import { CulturalOfferTypesComponent } from './cultural-content/cultural-offer-types/cultural-offer-types.component';
import { CulturalOfferSubtypesComponent } from './cultural-content/cultural-offer-subtypes/cultural-offer-subtypes.component';
import { HomeUserComponent } from './core/home-user/home-user.component';
import { MainAppComponent } from './core/main-app/main-app.component';
import {BarRatingModule} from 'ngx-bar-rating';
import { CulturalOffersComponent } from './cultural-content/cultural-offers/cultural-offers.component';
import { CulturalOfferDetailsComponent } from './cultural-content/cultural-offer-details/cultural-offer-details.component';

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        RegisterComponent,
        UserPageComponent,
        ToolbarUserComponent,
        NavbarUserComponent,
        UserSubsComponent,
        ChangePasswordDialogComponent,
        CulturalOfferTypesComponent,
        CulturalOfferSubtypesComponent,
        HomeUserComponent,
        MainAppComponent,
        CulturalOffersComponent,
        CulturalOfferDetailsComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        ReactiveFormsModule,
        MatInputModule,
        HttpClientModule,
        ToastrModule.forRoot(),
        MaterialModule,
        BarRatingModule
    ],
    entryComponents: [ChangePasswordDialogComponent],
    providers: [{provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true}],
    bootstrap: [AppComponent]
})
export class AppModule { }

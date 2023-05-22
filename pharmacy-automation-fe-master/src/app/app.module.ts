import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {UserProfileComponent} from './user-profile/user-profile.component';
import {MatGridListModule} from "@angular/material/grid-list";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ProductCardComponent} from './product-card/product-card.component';
import {MatCardModule} from "@angular/material/card";
import {NavMenuComponent} from './nav-menu/nav-menu.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from '@angular/material/button'
import {NgOptimizedImage} from "@angular/common";
import {LoginPageComponent} from './login-page/login-page.component';
import {MatIconModule} from "@angular/material/icon";
import {ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {MatInputModule} from "@angular/material/input";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {ApiUrlInterceptor} from "./_interceptors/api-url-interceptor";
import {ErrorInterceptor} from "./_interceptors/error-interceptor";
import {TokenInterceptor} from "./_interceptors/token-interceptor";
import {AuthGuardService} from "./_guards/AuthGuard";
import {ProductPageComponent} from './product-page/product-page.component';
import {MatChipsModule} from "@angular/material/chips";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTableModule} from "@angular/material/table";
import {BadGatewayRoutingModule} from "./bad-gateway/bad-gateway-routing.module";
import {SignupPageComponent} from './signup-page/signup-page.component';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {AdminPanelComponent} from './admin-panel/admin-panel.component';
import {MatTabsModule} from "@angular/material/tabs";
import {HomePageComponent} from './home-page/home-page.component';

@NgModule({
  declarations: [
    AppComponent,
    UserProfileComponent,
    ProductCardComponent,
    NavMenuComponent,
    LoginPageComponent,
    ProductPageComponent,
    SignupPageComponent,
    AdminPanelComponent,
    HomePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatGridListModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatToolbarModule,
    MatButtonModule,
    NgOptimizedImage,
    MatIconModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatInputModule,
    MatSnackBarModule,
    MatChipsModule,
    MatPaginatorModule,
    MatTableModule,
    BadGatewayRoutingModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTabsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ApiUrlInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
    AuthGuardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

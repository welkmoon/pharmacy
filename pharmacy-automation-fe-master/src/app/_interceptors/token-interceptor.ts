import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthenticationService} from "../_services/AuthenticationService";
import {environment} from "../../environments/environment";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(private authenticationService: AuthenticationService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // add authorization header with jwt token if available
    let newRequest = request;
    const token = this.authenticationService.getToken();

    if (token) {
      if (newRequest.url.includes(environment.API_URL)) {
        newRequest = newRequest.clone({
          setHeaders: {
            Authorization: `Bearer ${token}`,
          },
        });
      }

      newRequest = newRequest.clone({
        setHeaders: {
          'Content-Type': 'application/json',
        },
      });
    }

    return next.handle(newRequest);
  }
}

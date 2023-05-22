import {Injectable} from '@angular/core';
import {HttpClient, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {EMPTY, Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {Router} from '@angular/router';
import {SnackbarService} from "../snack-bar/snackbar.service";
import {AuthenticationService} from "../_services/AuthenticationService";
import {navigationRoutes} from "../navigationRoutes";


@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
    private http: HttpClient,
    private snackbarService: SnackbarService,
  ) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<any> {
    const token = this.authenticationService.getToken();
    let newRequest = request;
    return next.handle(newRequest).pipe(
      catchError((err) => {

        // Unauthorized error handler
        if (err.status === 401) {
          this.router.navigate([navigationRoutes.auth]);
        }

        // Bad Gateway error handler
        if (err.status === 0 || err.status === 502) {
          this.router.navigate([navigationRoutes.badGateway]);
          return EMPTY;
        }

        this.snackbarService.showErrorSnackBar(err.error);
        const consoleError = (err?.error && err?.error?.message);

        return throwError(consoleError);
      }),
    );
  }
}

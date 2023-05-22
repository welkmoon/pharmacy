import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {map} from 'rxjs/operators';
// @ts-ignore
import {User} from '../_models';
import {navigationRoutes} from "../navigationRoutes";

@Injectable({providedIn: 'root'})
export class AuthenticationService {
  private userSubject: BehaviorSubject<User | null>;
  private user: Observable<User | null>;
  public isAuthenticatedSubject$: BehaviorSubject<boolean>;

  constructor(
    private router: Router,
    private http: HttpClient
  ) {
    this.userSubject = new BehaviorSubject(JSON.parse(localStorage.getItem('user')!));
    this.user = this.userSubject.asObservable();
    this.isAuthenticatedSubject$ = new BehaviorSubject<boolean>(this._localStorageIsAuthenticated);
  }

  public get userValue() {
    return this.userSubject.value;
  }

  public getToken(): string {
    return localStorage.getItem("token");
  }

  public isAuthenticated(): boolean {
    return this.isAuthenticatedSubject$.value;
  }

  login(email: string, password: string) {
    return this.http.post<any>(`/auth`, {email, password})
      .pipe(map(token => {
        localStorage.setItem('token', token.value);
        this.isAuthenticatedSubject$.next(true);
        return token;
      }));
  }

  logout() {
    localStorage.removeItem('user');
    localStorage.removeItem('token');
    this.userSubject.next(null);
    this.isAuthenticatedSubject$.next(false);
    this.router.navigate([navigationRoutes.home]);
  }

  private get _localStorageIsAuthenticated() {
    const isAuthenticated = localStorage.getItem("token");
    return isAuthenticated ? true : false;
  }
}

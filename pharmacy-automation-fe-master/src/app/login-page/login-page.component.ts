import {Component, OnInit} from '@angular/core';
import {first} from "rxjs";
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../_services/AuthenticationService";
import {SnackbarService} from "../snack-bar/snackbar.service";
import {SnackbarTypes} from "../_enums/material-snackbar-types.enum";
import {navigationRoutes} from "../navigationRoutes";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  loginForm!: FormGroup;
  loading = false;
  submitted = false;
  error = '';

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private snack: SnackbarService,
  ) {
    // redirect to home if already logged in
    if (this.authenticationService.userValue) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    console.log("isAuthenticated: ", this.authenticationService.isAuthenticated());
    if (this.authenticationService.isAuthenticated()) {
      this.router.navigate([navigationRoutes.myProfile]);
    }
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.error = '';
    this.loading = true;
    this.authenticationService.login(this.loginForm.get('email').value, this.loginForm.get('password').value)
      .pipe(first())
      .subscribe({
        next: () => {
          this.snack.showSuccessSnackBar();
          this.router.navigate([navigationRoutes.myProfile]);
        },
        error: error => {
          this.snack.showSnackbar(SnackbarTypes.error, error, 4000);
          this.loading = false;
        }
      });
  }

}

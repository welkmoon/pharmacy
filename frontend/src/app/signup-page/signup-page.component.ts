import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../_services/AuthenticationService";
import {SnackbarService} from "../snack-bar/snackbar.service";
import {navigationRoutes} from "../navigationRoutes";
import {first} from "rxjs";
import {SnackbarTypes} from "../_enums/material-snackbar-types.enum";
import {UserService} from "../_services/UserService";
import {UserCreationRequest} from "../_models/UserCreationRequest";

@Component({
  selector: 'app-signup-page',
  templateUrl: './signup-page.component.html',
  styleUrls: ['./signup-page.component.scss']
})
export class SignupPageComponent implements OnInit {

  loginForm!: FormGroup;
  loading = false;
  submitted = false;
  confirmPasswordMatched = true;
  error = '';

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private snack: SnackbarService,
    private userService: UserService,
  ) {
    // redirect to home if already logged in
    if (this.authenticationService.userValue) {
      this.router.navigate([navigationRoutes.myProfile]);
    }
  }

  ngOnInit() {
    console.log("isAuthenticated: ", this.authenticationService.isAuthenticated());
    if (this.authenticationService.isAuthenticated()) {
      this.router.navigate([navigationRoutes.myProfile]);
    }
    this.loginForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required],
      birthDay: ['', Validators.required],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
    });
  }

  checkPasswordMatch() {
    let value = this.loginForm.get("password").value;
    let value1 = this.loginForm.get("confirmPassword").value;
    this.confirmPasswordMatched = value === value1;
    console.log("checking", value, value1);
    return this.confirmPasswordMatched;
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }
    if (!this.checkPasswordMatch()) {
      return;
    }

    this.error = '';
    this.loading = true;
    const request: UserCreationRequest = {
      firstName: this.loginForm.get('firstName').value,
      lastName: this.loginForm.get('lastName').value,
      email: this.loginForm.get('email').value,
      birthDay: this.loginForm.get('birthDay').value,
      password: this.loginForm.get('password').value,
    }
    this.userService.createProfile(request)
      .pipe(first())
      .subscribe({
        next: () => {
          this.snack.showSuccessSnackBar();
          this.router.navigate([navigationRoutes.auth]);
        },
        error: error => {
          this.snack.showSnackbar(SnackbarTypes.error, error, 4000);
          this.loading = false;
        }
      });
  }

}

import {CanActivate, Router} from "@angular/router";
import {Injectable} from "@angular/core";
import {UserService} from "../_services/UserService";
import {navigationRoutes} from "../navigationRoutes";

@Injectable()
export class AuthGuardService implements CanActivate {
  constructor(public userService: UserService, public router: Router) {
  }

  canActivate(): boolean {
    if (!this.userService.isAdmin()) {
      this.router.navigate([navigationRoutes.myProfile]);
      return false;
    }
    return true;
  }
}

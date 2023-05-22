import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {MyProfileDto} from "../_models/MyProfileDto";
import {UserCreationRequest} from "../_models/UserCreationRequest";

@Injectable({providedIn: 'root'})
export class UserService {

  constructor(private http: HttpClient) {
  }

  public createProfile(request: UserCreationRequest): Observable<any> {
    return this.http.post("/users", request);
  }

  public getMyProfile(): Observable<MyProfileDto> {
    return this.http.get<MyProfileDto>("/users/profile");
  }

  public isAdmin(): boolean {
    return this.getLocalUser().role === "ADMIN";
  }

  private getLocalUser() {
    return JSON.parse(localStorage.getItem("user"));
  }
}

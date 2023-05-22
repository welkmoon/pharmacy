import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({providedIn: 'root'})
export class HealthCheckService {

  constructor(private http: HttpClient) {
  }

  public check(): Observable<boolean> {
    return this.http.get<boolean>("/health-check");
  }
}

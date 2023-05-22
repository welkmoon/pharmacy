import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subject} from 'rxjs';
import {takeUntil} from 'rxjs/operators';
import {Router} from '@angular/router';
import {HealthCheckService} from "../_services/HealthCheckService";
import {navigationRoutes} from "../navigationRoutes";

@Component({
  selector: 'app-bad-gateway',
  templateUrl: './bad-gateway.component.html',
  styleUrls: ['./bad-gateway.component.scss'],
})
export class BadGatewayComponent implements OnInit, OnDestroy {
  private ngUnsubscribe = new Subject<void>();

  constructor(private router: Router, private healthCheckService: HealthCheckService) {
  }

  ngOnInit() {
    this.checkIsServiceWork();
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  public checkIsServiceWork() {
    this.healthCheckService
      .check()
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe(() => {
        this.router.navigate([navigationRoutes.home]);
      });
  }
}

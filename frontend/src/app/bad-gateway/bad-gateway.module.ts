import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BadGatewayComponent} from './bad-gateway.component';
import {BadGatewayRoutingModule} from './bad-gateway-routing.module';

@NgModule({
  declarations: [BadGatewayComponent],
  imports: [CommonModule, BadGatewayRoutingModule],
})
export class BadGatewayModule {
}

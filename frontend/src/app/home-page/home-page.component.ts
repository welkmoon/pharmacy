import {Component, OnInit} from '@angular/core';
import {ProductService} from "../_services/ProductService";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  constructor(public productService: ProductService) {
  }

  ngOnInit(): void {
  }

}

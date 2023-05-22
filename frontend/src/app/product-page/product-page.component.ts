import {Component, Input, OnInit} from '@angular/core';
import {PaginationDto} from "../_models/PaginationDto";
import {ProductDto} from "../_models/ProductDto";
import {PageDto} from "../_models/PageDto";
import {Observable} from "rxjs";

@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrls: ['./product-page.component.scss']
})
export class ProductPageComponent implements OnInit {
  @Input() productPage: Observable<PageDto<ProductDto>>;
  products?: ProductDto[];
  pagination?: PaginationDto;

  constructor() {
  }

  ngOnInit(): void {
    this.productPage.subscribe(page => {
      this.products = page.content;
      this.pagination = {
        page: +page.pageable.pageNumber + +1,
        size: page.pageable.pageSize,
        totalElements: page.totalElements,
        totalPages: page.totalPages
      }
    })
  }

}

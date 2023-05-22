import {Component, Input, OnInit} from '@angular/core';
import {ProductDto} from "../_models/ProductDto";
import {ProductService} from "../_services/ProductService";
import {SnackbarService} from "../snack-bar/snackbar.service";
import {AuthenticationService} from "../_services/AuthenticationService";

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.scss']
})
export class ProductCardComponent implements OnInit {
  @Input() product: ProductDto;
  @Input() showIcon: boolean = true;

  constructor(private productService: ProductService,
              private snackbarService: SnackbarService,
              public authService: AuthenticationService) {
  }

  ngOnInit(): void {
  }

  public toggleFavouriteProduct(product: ProductDto): boolean {
    console.log(product.uuid);
    console.log(product.isFavourite);
    if (product.isFavourite) {
      this.productService.removeFromFavourite(product.uuid)
        .subscribe(() => product.isFavourite = false);
      product.isFavourite = false;
    } else {
      this.productService.addToFavourite(product.uuid)
        .subscribe(() => product.isFavourite = true);
    }
    this.snackbarService.showSuccessSnackBar();
    return this.product.isFavourite;
  }

}

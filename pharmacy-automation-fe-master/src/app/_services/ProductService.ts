import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PageDto} from "../_models/PageDto";
import {ProductDto} from "../_models/ProductDto";

@Injectable({providedIn: 'root'})
export class ProductService {

  constructor(private http: HttpClient) {
  }

  public getProductPage(): Observable<PageDto<ProductDto>> {
    return this.http.get<PageDto<ProductDto>>("/products");
  }

  public getWishlistPage(): Observable<PageDto<ProductDto>> {
    return this.http.get<PageDto<ProductDto>>("/products/wishlist");
  }

  public addToFavourite(uuid: string) {
    return this.http.post(`/products/${uuid}/wishlist`, null);
  }

  public removeFromFavourite(uuid: string) {
    return this.http.delete(`/products/${uuid}/wishlist`);
  }
}

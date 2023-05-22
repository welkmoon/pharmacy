package kubg.edu.ua.automation.pharmacy.controller;

import jakarta.validation.Valid;    
import kubg.edu.ua.automation.pharmacy.dto.ProductDto;
import kubg.edu.ua.automation.pharmacy.dto.ProductRequest;
import kubg.edu.ua.automation.pharmacy.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductDto> getProducts(@SortDefault(sort = "name") Pageable pageable) {
        log.info("Getting product page");
        return productService.getProductPage(pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void createProduct(@RequestBody @Valid ProductRequest request) {
        log.info("Creating product");
        productService.create(request);
    }

    @PostMapping("/{productUuid}/wishlist")
    public void addToWishlist(@PathVariable String productUuid) {
        log.info("Adding product: {} to wishlist", productUuid);
        productService.addToWishlist(productUuid);
    }

    @DeleteMapping("/{productUuid}/wishlist")
    public void removeFromWishlist(@PathVariable String productUuid) {
        log.info("Removing product: {} to wishlist", productUuid);
        productService.removeFromWishlist(productUuid);
    }

    @GetMapping("/wishlist")
    public Page<ProductDto> getWishlistPage(Pageable pageable) {
        log.info("Getting wishlist");
        return productService.getWishlistPage(pageable);
    }

}

package kubg.edu.ua.automation.pharmacy.service;

import kubg.edu.ua.automation.pharmacy.annotation.Public;
import kubg.edu.ua.automation.pharmacy.dto.ProductDto;
import kubg.edu.ua.automation.pharmacy.dto.ProductRequest;
import kubg.edu.ua.automation.pharmacy.entity.MyUser;
import kubg.edu.ua.automation.pharmacy.entity.Product;
import kubg.edu.ua.automation.pharmacy.entity.WishProduct;
import kubg.edu.ua.automation.pharmacy.exception.NotFoundException;
import kubg.edu.ua.automation.pharmacy.mapper.ProductMapper;
import kubg.edu.ua.automation.pharmacy.repository.ProductRepository;
import kubg.edu.ua.automation.pharmacy.repository.WishProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final WishProductRepository wishProductRepository;
    private final MyUserService userService;
    private final ProductMapper mapper;

    @Public
    public Page<ProductDto> getProductPage(Pageable pageable) {
        return repository.getProductBy(pageable)
                .map(mapper::toDto)
                .map(this::setIsFavourite);
    }

    @Public
    public void create(ProductRequest request) {
        Product product = mapper.toEntity(request);
        repository.save(product);
    }

    @Public
    public void addToWishlist(String productUuid) {
        Product product = getByUuid(productUuid);
        MyUser currentUser = userService.getCurrentUser();
        if (wishProductRepository.findByUserAndProductUuid(currentUser, product.getUuid()).isPresent()) {
            return;
        }
        wishProductRepository.save(new WishProduct(currentUser, product));
    }

    @Public
    public void removeFromWishlist(String productUuid) {
        Product product = getByUuid(productUuid);
        MyUser currentUser = userService.getCurrentUser();
        wishProductRepository.findByUserAndProductUuid(currentUser, product.getUuid())
                .ifPresent(wishProductRepository::delete);
    }

    @Public
    public Page<ProductDto> getWishlistPage(Pageable pageable) {
        MyUser currentUser = userService.getCurrentUser();
        return wishProductRepository.findByUser(currentUser, pageable)
                .map(WishProduct::getProduct)
                .map(mapper::toDto)
                .map(this::setIsFavourite);
    }

    private ProductDto setIsFavourite(ProductDto productDto) {
        if (!userService.isAuthenticated()) {
            return productDto;
        }
        MyUser currentUser = userService.getCurrentUser();
        boolean isFavourite = wishProductRepository.existsByUserAndProductUuid(currentUser, productDto.getUuid());
        productDto.setIsFavourite(isFavourite);
        return productDto;
    }

    private Product getByUuid(String productUuid) {
        return repository.findByUuid(productUuid)
                .orElseThrow(() -> new NotFoundException("No product found with uuid: {}", productUuid));
    }
}

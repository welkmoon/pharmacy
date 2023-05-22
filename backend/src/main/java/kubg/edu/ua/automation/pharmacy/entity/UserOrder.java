package kubg.edu.ua.automation.pharmacy.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class UserOrder extends IdHolder {

    @NotNull
    @ManyToOne
    private MyUser user;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @NotNull
    private Instant time = Instant.now();


    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private List<Product> products = new ArrayList<>();

}

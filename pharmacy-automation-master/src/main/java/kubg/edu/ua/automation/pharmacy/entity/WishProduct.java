package kubg.edu.ua.automation.pharmacy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WishProduct extends IdHolder {

    @NotNull
    @ManyToOne
    private MyUser user;

    @NotNull
    @ManyToOne
    private Product product;

}

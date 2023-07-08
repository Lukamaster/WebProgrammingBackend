package com.webprogramming.backend.model;

import com.webprogramming.backend.model.identity.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;

    private Boolean status;

    @OneToMany(mappedBy = "shoppingCart")
    private List<WebProduct> products;

    @OneToMany(mappedBy = "shoppingCart")
    private List<Order> order;
}

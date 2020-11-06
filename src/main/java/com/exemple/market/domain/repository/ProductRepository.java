package com.exemple.market.domain.repository;

import com.exemple.market.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarseProduct(int quantity);
    Optional<Product> getProduct(int productId);
    Product save(Product product);
    void  delete(int productId);

//    con esta interfas definimos las reglas del domino que los repoitorio tendran que seguir dentro de una base de datos

}

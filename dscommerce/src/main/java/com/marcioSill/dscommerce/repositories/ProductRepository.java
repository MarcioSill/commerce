package com.marcioSill.dscommerce.repositories;

import com.marcioSill.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long>{
}

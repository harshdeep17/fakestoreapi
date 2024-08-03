package com.example.firstproject.repositories;

import com.example.firstproject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Override
    Optional<Product> findById(Long id);
    List<Product> findByTitle(String word);
    List<Product> findByTitleContains(String str);
    List<Product> findByTitleAndDescription(String title,String description);
    Optional<Product> findByImage(String url);

    @Override
    void delete(Product entity);
    @Override
    Product save(Product product); //create and update
}

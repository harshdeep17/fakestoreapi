package com.example.microservices.repositories;

import com.example.microservices.models.Product;
import com.example.microservices.projections.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Override
    Optional<Product> findById(Long id);
    @Override
    List<Product> findAll();
    List<Product> findByTitle(String word);
    List<Product> findByTitleContains(String str);
    List<Product> findByTitleAndDescription(String title,String description);
    Optional<Product> findByImage(String url);

    @Override
    void delete(Product entity);
    @Override
    Product save(Product product); //create and update

    //HQL
    @Query("select p.title as title, p.description as description from Product p where p.id = :id")
    ProductInfo someRandomQuery(@Param("id") Long id);

    //SQL Query -> native query
    @Query(value = "select title, description from product where id = :id", nativeQuery = true)
    ProductInfo someOtherRandomQuery(@Param("id") Long id);
}

package com.industrialscansystem.respository;

import com.industrialscansystem.Bean.DamageType;
import com.industrialscansystem.Bean.Log;
import com.industrialscansystem.Bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ProductRespository extends JpaRepository<Product,Integer> {

    @Query(value = "select * from product where product_id=?1 ",nativeQuery = true)
    public Product getProductByProductId(Integer product_id);

    @Query(value = "select * from product where product_name=?1 ",nativeQuery = true)
    public Product getProductByProductName(String product_name);

    @Query(value = "select * from product", nativeQuery = true)
    public List<Product> getAllProduct();
}

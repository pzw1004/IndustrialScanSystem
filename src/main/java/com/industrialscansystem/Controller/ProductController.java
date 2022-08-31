package com.industrialscansystem.Controller;


import com.industrialscansystem.Bean.Product;
import com.industrialscansystem.respository.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRespository productRespository;

    @RequestMapping(value = "/getAllProduct")
    public List<Product> getAllProduct(){
        return productRespository.getAllProduct();
    }

    @RequestMapping(value = "/getProductByProductId/{product_id}")
    public Product getProductByProductId(@PathVariable("product_id") int product_id){
        return productRespository.getProductByProductId(product_id);
    }

    @RequestMapping(value = "/getProductByProductName/{product_name}")
    public Product getProductByProductName(@PathVariable("product_name") String product_name){
        return productRespository.getProductByProductName(product_name);
    }
}

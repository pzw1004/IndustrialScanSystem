package com.industrialscansystem.Controller;


import com.industrialscansystem.Bean.Product;
import com.industrialscansystem.respository.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/getProductByProductId")
    public Product getProductByProductId(@RequestParam("product_id") Integer product_id){
//        System.out.println(product_id);
        return productRespository.getProductByProductId(product_id);
    }

    @RequestMapping(value = "/getProductByProductName/{product_name}")
    public Product getProductByProductName(@PathVariable("product_name") String product_name){
        return productRespository.getProductByProductName(product_name);
    }
}

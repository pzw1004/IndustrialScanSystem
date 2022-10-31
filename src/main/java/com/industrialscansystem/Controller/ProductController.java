package com.industrialscansystem.Controller;


import com.industrialscansystem.Bean.Product;
import com.industrialscansystem.respository.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Product getProductByProductId(@PathVariable("product_id") Integer product_id){
        int i = product_id;
//        System.out.println(product_id);
        return productRespository.getProductByProductId(product_id);
    }
    @RequestMapping(value = "/deleteProductByProductId/{product_id}")
    public String deleteProductByProductId(@PathVariable("product_id") Integer product_id){
        productRespository.deleteProductById(product_id);
//        System.out.println(product_id);
        return "success";
    }

    @RequestMapping(value = "/getProductByProductName/{product_name}")
    public Product getProductByProductName(@PathVariable("product_name") String product_name){
        return productRespository.getProductByProductName(product_name);
    }

    @RequestMapping(value = "/addProduct/{product_name}")
    public String addProductByName(@PathVariable("product_name") String product_name){
        productRespository.addProduct(product_name);
        return "success";
    }

    @RequestMapping(value = "/updateProduct")
    public String updateProductById(@RequestParam("product_id") Integer id,@RequestParam("product_name") String name){
        Product p = productRespository.getProductByProductId(id);
        p.setProduct_name(name);
        productRespository.save(p);
        return "success";
    }
}

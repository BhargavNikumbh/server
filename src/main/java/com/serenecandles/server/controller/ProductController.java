package com.serenecandles.server.controller;

import com.serenecandles.server.model.Category;
import com.serenecandles.server.model.Image;
import com.serenecandles.server.model.Product;
import com.serenecandles.server.model.ProductImage;
import com.serenecandles.server.repo.ProductImageRepository;
import com.serenecandles.server.service.impl.ProductServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/admin-user")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping(value={"/product"}, consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addProduct(@RequestPart("product")Product product,
                                        @RequestPart("imageFile")ArrayList<MultipartFile> file){
        try{
            Set<ProductImage> images = uploadImage(file);
            product.setProductImages(images);
            Product local = this.productService.createProduct(product);

            return ResponseEntity.status(HttpStatus.OK).body(local);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    public Set<ProductImage> uploadImage(ArrayList<MultipartFile> multipartfiles) throws IOException {
        Set<ProductImage> images = new HashSet<>();
        for(MultipartFile file : multipartfiles){
            ProductImage image = new ProductImage(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            images.add(image);
        }
        return images;
    }

    @GetMapping("/product")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/product/{productId}")
    public Product getProductById(@PathVariable("productId") Long productId){
        return this.productService.getProductDetailsById(productId);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId){
        this.productService.deleteProduct(productId);
    }


}

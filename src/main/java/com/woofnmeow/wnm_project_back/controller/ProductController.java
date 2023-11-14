package com.woofnmeow.wnm_project_back.controller;

import com.woofnmeow.wnm_project_back.dto.AddProductReqDto;
import com.woofnmeow.wnm_project_back.dto.EditProductReqDto;
import com.woofnmeow.wnm_project_back.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/api/admin/product")
    public ResponseEntity<?> addProduct(@RequestBody AddProductReqDto addProductReqDto) {
        return ResponseEntity.ok().body(productService.addProduct(addProductReqDto));
    }

    @GetMapping("/api/product/{productId}")
    public ResponseEntity<?> getProductByProductId(@PathVariable int productId) {
        return ResponseEntity.ok().body(productService.getProductByProductId(productId));
    }

    @GetMapping("/api/products/{petTypeName}/{productCategoryName}")
    public ResponseEntity<?> getProducts(@PathVariable String petTypeName,
                                         @PathVariable String productCategoryName,
                                         @RequestParam String option,
                                         @RequestParam String value,
                                         @RequestParam String sort,
                                         @RequestParam int page
    ) {
        return ResponseEntity.ok().body(productService.getProducts
                (petTypeName,
                        productCategoryName,
                        option,
                        value,
                        sort,
                        page));
    }

    @PutMapping("/api/admin/product/{productId}")
    public ResponseEntity<?> editProduct(@PathVariable int productId, @RequestBody EditProductReqDto editProductReqDto) {
        System.out.println(editProductReqDto);
        return ResponseEntity.ok().body(productService.editProduct(productId, editProductReqDto));
    }

    @DeleteMapping("/api/admin/product/{productId}")
    public ResponseEntity<?> removeProduct(@PathVariable int productId) {
        return ResponseEntity.ok().body(productService.removeProduct(productId));
    }

}

package com.woofnmeow.wnm_project_back.controller;

import com.woofnmeow.wnm_project_back.dto.request.AddProductReqDto;
import com.woofnmeow.wnm_project_back.dto.request.EditProductReqDto;
import com.woofnmeow.wnm_project_back.dto.request.SearchMasterProductReqDto;
import com.woofnmeow.wnm_project_back.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    // C
    @PostMapping("/api/admin/product")
    public ResponseEntity<?> addProduct(@RequestBody AddProductReqDto addProductReqDto) {
        return ResponseEntity.ok().body(productService.addProduct(addProductReqDto));
    }

    @PostMapping("/api/admin/incoming/{productDtlId}/{count}")
    public ResponseEntity<?> addIncomingQuantity(@PathVariable int productDtlId, @PathVariable int count) {
        return ResponseEntity.ok().body(productService.addIncomingQuantity(productDtlId, count));
    }

    @PostMapping("/api/admin/outgoing/{productDtlId}/{count}")
    public ResponseEntity<?> addOutgoingQuantity(@PathVariable int productDtlId, @PathVariable int count) {
        return ResponseEntity.ok().body(productService.addOutgoingQuantity(productDtlId, count));
    }





    // R
    @GetMapping("/api/admin/incoming/{productDtlId}")
    public ResponseEntity<?> getIncomingByProductDtlId(@PathVariable int productDtlId) {
        return ResponseEntity.ok().body(productService.getIncomingByProductDtlId(productDtlId));
    }

    @GetMapping("/api/admin/outgoing/{productDtlId}")
    public ResponseEntity<?> getOutgoingByProductDtlId(@PathVariable int productDtlId) {
        return ResponseEntity.ok().body(productService.getOutgoingByProductDtlId(productDtlId));
    }

    @GetMapping("/api/product/detail/{productDtlId}")
    public ResponseEntity<?> getProductByProductDtlId(@PathVariable int productDtlId) {
        return ResponseEntity.ok().body(productService.getProductByProductDtlId(productDtlId));
    }

    @GetMapping("/api/product/master/{productMstId}")
    public ResponseEntity<?> getProductByProductMstId(@PathVariable int productMstId) {
        return ResponseEntity.ok().body(productService.getProductByProductMstId(productMstId));
    }

    @GetMapping("api/products/sizes")
    public ResponseEntity<?> searchProductsWithAllSizes(SearchMasterProductReqDto searchMasterProductReqDto) {
        return ResponseEntity.ok().body(productService.searchProductsWithAllSizes(searchMasterProductReqDto));
    }

    @GetMapping("api/products/minmax")
    public ResponseEntity<?> searchProductsWithMinPriceAndMaxPrice(SearchMasterProductReqDto searchMasterProductReqDto) {
        return ResponseEntity.ok().body(productService.searchProductsWithMinPriceAndMaxPrice(searchMasterProductReqDto));
    }

    @GetMapping("api/products/count")
    public ResponseEntity<?> getCountOfSearchedProducts(SearchMasterProductReqDto searchMasterProductReqDto) {
        System.out.println(searchMasterProductReqDto);
        return ResponseEntity.ok().body(productService.getCountOfSearchedProducts(searchMasterProductReqDto));
    }




    // U
    @PutMapping("/api/admin/product/{productMstId}")
    public ResponseEntity<?> editProduct(@PathVariable int productMstId, @RequestBody EditProductReqDto editProductReqDto) {
        return ResponseEntity.ok().body(productService.editProduct(productMstId, editProductReqDto));
    }




    // D
    @DeleteMapping("/api/admin/product/{productMstId}")
    public ResponseEntity<?> removeProduct(@PathVariable int productMstId) {
        return ResponseEntity.ok().body(productService.removeProduct(productMstId));
    }

}
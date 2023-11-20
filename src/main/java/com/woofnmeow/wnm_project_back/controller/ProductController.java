package com.woofnmeow.wnm_project_back.controller;

import com.woofnmeow.wnm_project_back.dto.AddProductReqDto;
import com.woofnmeow.wnm_project_back.dto.EditProductReqDto;
import com.woofnmeow.wnm_project_back.dto.GetSearchedMstProductsReqDto;
import com.woofnmeow.wnm_project_back.dto.SearchMasterProductReqDto;
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

    @PostMapping("/api/admin/incoming/{productDtlId}")
    public ResponseEntity<?> incomingQuantity(@PathVariable int productDtlId, @RequestBody int count) {
        return ResponseEntity.ok().body(productService.incomingQuantity(productDtlId, count));
    }

    @PutMapping("/api/admin/incoming/{incomingHistoryId}")
    public ResponseEntity<?> updateIncomingQuantity(@PathVariable int incomingHistoryId, @RequestBody int count) {
        return ResponseEntity.ok().body(productService.updateIncomingQuantity(incomingHistoryId, count));
    }

    @DeleteMapping("/api/admin/incoming/{incomingHistoryId}")
    public ResponseEntity<?> deleteIncomingQuantity(@PathVariable int incomingHistoryId) {
        return ResponseEntity.ok().body(productService.deleteIncomingQuantity(incomingHistoryId));
    }

    @PostMapping("/api/outgoing/{productDtlId}")
    public ResponseEntity<?> outgoingQuantity(@PathVariable int productDtlId, @RequestBody int count) {
        return ResponseEntity.ok().body(productService.outgoingQuantity(productDtlId, count));
    }

    @GetMapping("/api/incoming/{productDtlId}")
    public ResponseEntity<?> getIncomingByProductDtlId(@PathVariable int productDtlId) {
        return ResponseEntity.ok().body(productService.getIncomingByProductDtlId(productDtlId));
    }

    @GetMapping("/api/outgoing/{productDtlId}")
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

    @GetMapping("/api/admin/products")
    public ResponseEntity<?> getProducts(SearchMasterProductReqDto searchMasterProductReqDto) {
        return ResponseEntity.ok().body(productService.getProducts(searchMasterProductReqDto));
    }
    @GetMapping("/api/products")
    public ResponseEntity<?> getSearchedProducts(SearchMasterProductReqDto searchMasterProductReqDto) {
        return ResponseEntity.ok().body(productService.getSearchedProducts(searchMasterProductReqDto));
    }

    @GetMapping("api/product/master")
    public ResponseEntity<?> searchMasterProduct(SearchMasterProductReqDto searchMasterProductReqDto) {
        return ResponseEntity.ok().body(productService.searchMasterProduct(searchMasterProductReqDto));
    }

    @PutMapping("/api/admin/product/{productMstId}")
    public ResponseEntity<?> editProduct(@PathVariable int productMstId, @RequestBody EditProductReqDto editProductReqDto) {
        return ResponseEntity.ok().body(productService.editProduct(productMstId, editProductReqDto));
    }

    @DeleteMapping("/api/admin/product/{productMstId}")
    public ResponseEntity<?> removeProduct(@PathVariable int productMstId) {
        return ResponseEntity.ok().body(productService.removeProduct(productMstId));
    }
}

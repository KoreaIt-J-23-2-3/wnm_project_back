package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.request.AddProductReqDto;
import com.woofnmeow.wnm_project_back.dto.request.EditProductReqDto;
import com.woofnmeow.wnm_project_back.dto.request.SearchMasterProductReqDto;
import com.woofnmeow.wnm_project_back.dto.response.GetAllProductsRespDto;
import com.woofnmeow.wnm_project_back.dto.response.GetIncomingAndOutgoingRespDto;
import com.woofnmeow.wnm_project_back.dto.response.GetProductRespDto;
import com.woofnmeow.wnm_project_back.dto.response.SearchMasterProductRespDto;
import com.woofnmeow.wnm_project_back.entity.Incoming;
import com.woofnmeow.wnm_project_back.entity.Outgoing;
import com.woofnmeow.wnm_project_back.entity.ProductMst;
import com.woofnmeow.wnm_project_back.exception.CartException;
import com.woofnmeow.wnm_project_back.exception.ProductException;
import com.woofnmeow.wnm_project_back.repository.ProductMapper;
import com.woofnmeow.wnm_project_back.vo.GetAllProductsVo;
import com.woofnmeow.wnm_project_back.vo.GetProductVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;


    // C
    @Transactional(rollbackFor = Exception.class)
    public boolean addProduct(AddProductReqDto addProductReqDto) {
        try {
            Map<String, Object> map = new HashMap<>();
            ProductMst productMst = addProductReqDto.toEntity();
            productMapper.addProductMaster(productMst);

            map.put("productMstId", productMst.getProductMstId());
            map.put("price", addProductReqDto.getPrice());

            if(addProductReqDto.getProductCategoryId() == 4 && addProductReqDto.getPetTypeId() == 1) {
                map.put("sizeId", 2);
                for(int i = 0; i < 6; i++) {
                    productMapper.addProductDetail(map);
                    map.replace("sizeId", i + 3);
                }
            }else {
                map.put("sizeId", 1);
                productMapper.addProductDetail(map);
            }
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("상품 오류", "상품 추가 중 오류가 발생하였습니다.");
            throw new ProductException(errorMap);
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean addIncomingQuantity(int productDtlId, int count) {
        Map<String, Object> map = new HashMap<>();
        map.put("productDtlId", productDtlId);
        map.put("count", count);
        boolean success = productMapper.addIncomingQuantity(map) > 0;
        if(!success) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("입고 오류", "상품 입고 중 오류가 발생하였습니다.");
            throw new ProductException(errorMap);
        }
        return success;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean addOutgoingQuantity(int productDtlId, int count) {
        Map<String, Object> map = new HashMap<>();
        map.put("productDtlId", productDtlId);
        map.put("count", count);
        boolean success = productMapper.addOutgoingQuantity(map) > 0;
        if(!success) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("출고 오류", "상품 출고 중 오류가 발생하였습니다.");
            throw new ProductException(errorMap);
        }
        return success;
    }










    // R
    public List<GetIncomingAndOutgoingRespDto> getIncomingByProductDtlId(int productDtlId) {
        List<GetIncomingAndOutgoingRespDto> result = new ArrayList<>();
        try {
            result = productMapper.selectIncomingByDtlId(productDtlId).stream().map(Incoming::toRespDto).collect(Collectors.toList());
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("입고 오류", "입고 정보를 조회하는 중 오류가 발생하였습니다.");
            throw new ProductException(errorMap);
        }
        return result;
    }

    public List<GetIncomingAndOutgoingRespDto> getOutgoingByProductDtlId(int productDtlId) {
        List<GetIncomingAndOutgoingRespDto> result = new ArrayList<>();
        try {
            result = productMapper.selectOutgoingByDtlId(productDtlId).stream().map(Outgoing::toRespDto).collect(Collectors.toList());
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("출고 오류", "출고 정보를 조회하는 중 오류가 발생하였습니다.");
            throw new ProductException(errorMap);
        }
        return result;
    }

    public GetProductRespDto getProductByProductDtlId(int productDtlId) {
        GetProductRespDto result = null;
        try {
            result = productMapper.selectProductByProductDtlId(productDtlId).toProductRespDto();
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("상품 오류", "상품 정보 조회 중 오류가 발생하였습니다.");
            throw new ProductException(errorMap);
        }
        return result;
    }

    public GetProductRespDto getProductByProductMstId(int productMstId) {
        GetProductRespDto result = null;
        try {
            result = productMapper.selectProductByProductMstId(productMstId).toProductRespDto();
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("상품 오류", "상품 정보 조회 중 오류가 발생하였습니다.");
            throw new ProductException(errorMap);
        }
        return result;
    }

    public List<GetAllProductsRespDto> searchProductsWithMinPriceAndMaxPrice(SearchMasterProductReqDto searchMasterProductReqDto) {
        List<GetAllProductsVo> getAllProductsVo = productMapper.searchProductsWithMinPriceAndMaxPrice(searchMasterProductReqDto.toSearchProduct());
        List<GetAllProductsRespDto> respDto = new ArrayList<>();

        try {
            getAllProductsVo.forEach(vo -> {
                        String allSizeAndPrice = vo.getSizeAndPrice();
                        String[] sizeAndPrice = allSizeAndPrice.split(", ");
                        List<String> priceList = new ArrayList<>();
                        for(String price: sizeAndPrice) {
                            priceList.add(price);
                        }
                        String minPrice = priceList.get(0).replace("/ ", ": ");
                        String maxPrice = "";
                        if (priceList.size() == 2) {
                            maxPrice = priceList.get(1).replace("/ ", ": ");
                        }
                        respDto.add(vo.toRespDto(minPrice, maxPrice));
                    }
            );
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("상품 오류", "상품 검색 중 오류가 발생하였습니다.");
            throw new ProductException(errorMap);
        }
        return respDto;
    }

    public int getCountOfSearchedProducts(SearchMasterProductReqDto searchMasterProductReqDto) {
        int count = 0;
        try {
            count = productMapper.selectCountOfSearchedProducts(searchMasterProductReqDto.toSearchProduct());
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("상품 오류", "상품 리스트를 불러오는 중 오류가 발생하였습니다.");
        }
        return count;
    }

    public List<SearchMasterProductRespDto> searchProductsWithAllSizes(SearchMasterProductReqDto searchMasterProductReqDto) {
        List<GetProductVo> getProductVo = productMapper.searchProductsWithAllSizes(searchMasterProductReqDto.toVo());
        Map<String, Object> map = new HashMap<>();
        List<SearchMasterProductRespDto> reqList= new ArrayList<>();

        try {
            getProductVo.forEach(vo -> {
                String allSizeAndPrice = vo.getSizeAndPrice();
                String[] sizeAndPrice = allSizeAndPrice.split(", ");
                for (int i = 0; i < sizeAndPrice.length; i++) {
                    String[] sizes = sizeAndPrice[i].split("/ ", 0);
                    String[] prices = sizeAndPrice[i].split("/ ", 1);
                    String size = sizes[0].substring(1);
                    String price = prices[0].substring(prices[0].indexOf("/ ") + 2).replace(")", "");
                    map.put(size, price.toString());
                }
                reqList.add(vo.toRespDto(map));
            });
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("상품 오류", "상품 검색 중 오류가 발생하였습니다.");
            throw new ProductException(errorMap);
        }
        return reqList;
    }




    // U
    @Transactional(rollbackFor = Exception.class)
    public boolean updateIncomingQuantity(int incomingHistoryId, int count) {
        Map<String, Object> map = new HashMap<>();
        map.put("incomingHistoryId", incomingHistoryId);
        map.put("count", count);
        boolean success = productMapper.updateIncomingQuantity(map) > 0;
        if(!success) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("입고 오류", "입고 정보 갱신 중 오류가 발생하였습니다.");
            throw new ProductException(errorMap);
        }
        return success;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean editProduct(int productMstId, EditProductReqDto editProductReqDto) {
        Map<String, Object> mstReqMap = new HashMap<>();
        mstReqMap.put("productMstId", productMstId);
        mstReqMap.put("productName", editProductReqDto.getProductName());
        mstReqMap.put("productDetailText", editProductReqDto.getProductDetailText());
        mstReqMap.put("productThumbnailUrl", editProductReqDto.getProductThumbnailUrl());
        mstReqMap.put("productDetailUrl", editProductReqDto.getProductDetailUrl());
        productMapper.updateProductMst(mstReqMap);

        if(editProductReqDto.getNo().equals("")) {
            Map<String, Integer> dtlReqMap = new HashMap<>();
            dtlReqMap.put("2", Integer.parseInt(editProductReqDto.getXS()));
            dtlReqMap.put("3", Integer.parseInt(editProductReqDto.getS()));
            dtlReqMap.put("4", Integer.parseInt(editProductReqDto.getM()));
            dtlReqMap.put("5", Integer.parseInt(editProductReqDto.getL()));
            dtlReqMap.put("6", Integer.parseInt(editProductReqDto.getXL()));
            dtlReqMap.put("7", Integer.parseInt(editProductReqDto.getXXL()));

            for (Map.Entry<String, Integer> entry : dtlReqMap.entrySet()) {
                int sizeId = Integer.parseInt((String) entry.getKey());
                Integer price = entry.getValue();
                productMapper.updateProductDtl(productMstId, sizeId, price);
            }
        }else {
            productMapper.updateProductDtl(productMstId, 1, Integer.parseInt(editProductReqDto.getNo()));
        }
        return true;
    }




    // D
    @Transactional(rollbackFor = Exception.class)
    public boolean removeProduct(int productMstId) {
        boolean success = productMapper.deleteProduct(productMstId) > 0;
        if(!success) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("상품 오류", "상품 삭제 중 오류가 발생하였습니다.");
            throw new ProductException(errorMap);
        }
        return success;
    }
}

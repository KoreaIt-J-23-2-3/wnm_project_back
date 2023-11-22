package com.woofnmeow.wnm_project_back.service;

import com.woofnmeow.wnm_project_back.dto.*;
import com.woofnmeow.wnm_project_back.entity.Incoming;
import com.woofnmeow.wnm_project_back.entity.Outgoing;
import com.woofnmeow.wnm_project_back.entity.ProductMst;
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

    @Transactional(rollbackFor = Exception.class)
    public boolean addProduct(AddProductReqDto addProductReqDto) {
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
        return true;
    }

    public boolean incomingQuantity(int productDtlId, int count) {
        Map<String, Object> map = new HashMap<>();
        map.put("productDtlId", productDtlId);
        map.put("count", count);
        return productMapper.incomingQuantity(map) > 0;
    }

    public boolean updateIncomingQuantity(int incomingHistoryId, int count) {
        Map<String, Object> map = new HashMap<>();
        map.put("incomingHistoryId", incomingHistoryId);
        map.put("count", count);
        return productMapper.updateIncomingQuantity(map) > 0;
    }

    public boolean deleteIncomingQuantity(int incomingHistoryId) {
        return productMapper.deleteIncomingQuantity(incomingHistoryId) > 0;
    }


    public boolean outgoingQuantity(int productDtlId, int count) {
        Map<String, Object> map = new HashMap<>();
        map.put("productDtlId", productDtlId);
        map.put("count", count);
        return productMapper.outgoingQuantity(map) > 0;
    }

    public List<GetIncomingAndOutgoingRespDto> getIncomingByProductDtlId(int productDtlId) {
        return productMapper.getIncomingByDtlId(productDtlId).stream().map(Incoming::toRespDto).collect(Collectors.toList());
    }

    public List<GetIncomingAndOutgoingRespDto> getOutgoingByProductDtlId(int productDtlId) {
        return productMapper.getOutgoingByDtlId(productDtlId).stream().map(Outgoing::toRespDto).collect(Collectors.toList());
    }

    public GetProductRespDto getProductByProductDtlId(int productDtlId) {
        return productMapper.getProductByProductDtlId(productDtlId).toProductRespDto();
    }

    public GetProductRespDto getProductByProductMstId(int productMstId) {
        return productMapper.getProductByProductMstId(productMstId).toProductRespDto();
    }

    public List<GetMasterProductRespDto> getProducts(SearchMasterProductReqDto searchMasterProductReqDto) {
        return productMapper.getMasterProductList(searchMasterProductReqDto.toVo()).stream().map(ProductMst::toMasterProductRespDto).collect(Collectors.toList());
    }

    public List<GetAllProductsRespDto> getAllProducts(SearchMasterProductReqDto searchMasterProductReqDto) {
        List<GetAllProductsVo> getAllProductsVo = productMapper.getAllProductMst(searchMasterProductReqDto.toSearchProduct());
        List<GetAllProductsRespDto> respDto = new ArrayList<>();
        Map<String, Object> respMap = new HashMap<>();

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
        return respDto;
    }

    public int getBoardCount(SearchMasterProductReqDto searchMasterProductReqDto) {
        int count = productMapper.getProductCount(searchMasterProductReqDto.toSearchProduct());
        System.out.println("service : " + count);
        return count;
    }

    public List<SearchMasterProductRespDto> searchMasterProduct(SearchMasterProductReqDto searchMasterProductReqDto) {
        List<GetProductVo> getProductVo = productMapper.searchProductMst(searchMasterProductReqDto.toVo());
        Map<String, Object> map = new HashMap<>();
        List<SearchMasterProductRespDto> reqList= new ArrayList<>();

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
        return reqList;
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
            productMapper.updateProductDtl(productMstId, 2, Integer.parseInt(editProductReqDto.getXS()));
            productMapper.updateProductDtl(productMstId, 3, Integer.parseInt(editProductReqDto.getS()));
            productMapper.updateProductDtl(productMstId, 4, Integer.parseInt(editProductReqDto.getM()));
            productMapper.updateProductDtl(productMstId, 5, Integer.parseInt(editProductReqDto.getL()));
            productMapper.updateProductDtl(productMstId, 6, Integer.parseInt(editProductReqDto.getXL()));
            productMapper.updateProductDtl(productMstId, 7, Integer.parseInt(editProductReqDto.getXXL()));
        }else {
            productMapper.updateProductDtl(productMstId, 1, Integer.parseInt(editProductReqDto.getNo()));
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean removeProduct(int productMstId) {
        return productMapper.deleteProduct(productMstId) > 0;
    }
}
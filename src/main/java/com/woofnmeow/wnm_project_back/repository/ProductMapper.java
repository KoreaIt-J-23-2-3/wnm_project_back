package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.dto.SearchMasterProductReqDto;
import com.woofnmeow.wnm_project_back.entity.ProductDtl;
import com.woofnmeow.wnm_project_back.entity.ProductMst;
import com.woofnmeow.wnm_project_back.vo.GetProductVo;
import com.woofnmeow.wnm_project_back.vo.SearchMasterProductVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface ProductMapper {
    @Options(useGeneratedKeys = true, keyProperty = "productMstId")
    public Integer addProductMaster(ProductMst productMst);
    public Integer addProductDetail(Map<String, Object> map);
    public Integer incomingQuantity(Map<String, Object> map);
    public Integer updateIncomingQuantity(Map<String, Object> map);
    public Integer deleteIncomingQuantity(int incomingHistoryId);
    public Integer outgoingQuantity(Map<String, Object> map);
    public ProductMst getProductByProductDtlId(int productDtlId);
    public ProductMst getProductByProductMstId(int productMstId);
    public List<GetProductVo> searchProductMst(SearchMasterProductVo searchMasterProductVo);
    public List<ProductMst> getMasterProductList(SearchMasterProductVo searchMasterProductVo);
    public Integer updateProductMst(Map<String, Object> map);
    public Integer updateProductDtl(int productMstId, int sizeId, int price);
    public Integer deleteProduct(int productMstId);
}

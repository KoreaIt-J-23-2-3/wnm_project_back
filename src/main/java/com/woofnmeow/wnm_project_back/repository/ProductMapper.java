package com.woofnmeow.wnm_project_back.repository;

import com.woofnmeow.wnm_project_back.entity.Incoming;
import com.woofnmeow.wnm_project_back.entity.Outgoing;
import com.woofnmeow.wnm_project_back.entity.ProductMst;
import com.woofnmeow.wnm_project_back.vo.GetAllProductsVo;
import com.woofnmeow.wnm_project_back.vo.GetProductVo;
import com.woofnmeow.wnm_project_back.vo.SearchMasterProductVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {
    @Options(useGeneratedKeys = true, keyProperty = "productMstId")
    public Integer addProductMaster(ProductMst productMst);
    public Integer addProductDetail(Map<String, Object> map);
    public Integer incomingQuantity(Map<String, Object> map);
    public Integer updateIncomingQuantity(Map<String, Object> map);
    public Integer deleteIncomingQuantity(int incomingHistoryId);
    public Integer outgoingQuantity(Map<String, Object> map);
    public List<Incoming> getIncomingByDtlId(int productDtlId);
    public List<Outgoing> getOutgoingByDtlId(int productDtlId);
    public ProductMst getProductByProductDtlId(int productDtlId);
    public ProductMst getProductByProductMstId(int productMstId);
    public List<GetProductVo> searchProductMst(SearchMasterProductVo searchMasterProductVo);
    public List<GetAllProductsVo> getAllProductMst(SearchMasterProductVo searchMasterProductVo);
    public Integer getProductCount(SearchMasterProductVo searchMasterProductVo);
    public List<ProductMst> getMasterProductList(SearchMasterProductVo searchMasterProductVo);
    public Integer updateProductMst(Map<String, Object> map);
    public Integer updateProductDtl(int productMstId, int sizeId, int price);
    public Integer deleteProduct(int productMstId);
}

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
    // C
    @Options(useGeneratedKeys = true, keyProperty = "productMstId")
    public Integer addProductMaster(ProductMst productMst);
    public Integer addProductDetail(Map<String, Object> map);
    public Integer addIncomingQuantity(Map<String, Object> map);
    public Integer addOutgoingQuantity(Map<String, Object> map);

    // R
    public List<Incoming> selectIncomingByDtlId(int productDtlId);
    public List<Outgoing> selectOutgoingByDtlId(int productDtlId);
    public ProductMst selectProductByProductDtlId(int productDtlId);
    public ProductMst selectProductByProductMstId(int productMstId);
    public List<GetProductVo> searchProductsWithAllSizes(SearchMasterProductVo searchMasterProductVo);
    public List<GetAllProductsVo> searchProductsWithMinPriceAndMaxPrice(SearchMasterProductVo searchMasterProductVo);
    public Integer selectCountOfSearchedProducts(SearchMasterProductVo searchMasterProductVo);

    // U
    public Integer updateIncomingQuantity(Map<String, Object> map);
    public Integer updateProductMst(Map<String, Object> map);
    public Integer updateProductDtl(int productMstId, int sizeId, int price);

    // D
    public Integer deleteProduct(int productMstId);

}

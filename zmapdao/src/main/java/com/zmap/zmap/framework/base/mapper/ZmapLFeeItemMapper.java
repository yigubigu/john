package com.zmap.zmap.framework.base.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.framework.base.model.ZmapLFeeItemModel;

@Mapper
public interface ZmapLFeeItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_l_fee_item
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String feeItemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_l_fee_item
     *
     * @mbg.generated
     */
    int insert(ZmapLFeeItemModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_l_fee_item
     *
     * @mbg.generated
     */
    int insertSelective(ZmapLFeeItemModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_l_fee_item
     *
     * @mbg.generated
     */
    ZmapLFeeItemModel selectByPrimaryKey(String feeItemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_l_fee_item
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ZmapLFeeItemModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_l_fee_item
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ZmapLFeeItemModel record);
    /************************** 手动添加 **************************/
    ZmapLFeeItemModel selectByItemCodeAndHosCode(ZmapLFeeItemModel record);
    ZmapLFeeItemModel selectByHisPrimaryKey(ZmapLFeeItemModel record);
    int deleteByHisPrimaryKey(ZmapLFeeItemModel record);
}
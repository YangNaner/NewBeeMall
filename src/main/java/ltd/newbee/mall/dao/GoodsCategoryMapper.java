package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.GoodsCategory;
import ltd.newbee.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsCategoryMapper {

    //    分页查询
    List<GoodsCategory> findGoodsCategoryList(PageQueryUtil pageUtil);

    // 总记录数
    int getTotalGoodsCategories(PageQueryUtil pageUtil);

    //    通过主键查询
    GoodsCategory selectByPrimaryKey(Long categoryId);

    // 新增
    int insertSelective(GoodsCategory record);

    // 通过等级和名称查询单条数据
    GoodsCategory selectByLevelAndName(@Param("categoryLevel") Byte categoryLevel, @Param("categoryName") String categoryName);

    // 删除
    int deleteBatch(Integer[] ids);

    // 根据等级ID查询
    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(@Param("parentIds") List<Long> parentIds, @Param("categoryLevel") int categoryLevel, @Param("number") int number);
}

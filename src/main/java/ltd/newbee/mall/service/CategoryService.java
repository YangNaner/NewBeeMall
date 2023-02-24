package ltd.newbee.mall.service;

import ltd.newbee.mall.controller.vo.IndexCategoryVO;
import ltd.newbee.mall.controller.vo.SearchPageCategoryVO;
import ltd.newbee.mall.entity.GoodsCategory;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

import java.util.List;

public interface CategoryService {
    //    分页查询
    PageResult getCategorisPage(PageQueryUtil pageUtil);

    //    新增
    String saveCategory(GoodsCategory goodsCategory);

    //    通过主键查询
//    GoodsCategory selectByPrimaryKey(Long categoryId);

    //  删除
    Boolean deleteBatch(Integer[] ids);

    //    根据等级和ID查询
    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel);

    GoodsCategory getGoodsCategoryById(Long id);

    List<IndexCategoryVO> getCategoriesForIndex();

//    返回分类数据(搜索页调用)
    SearchPageCategoryVO getCategoriesForSearch(Long categoryId);
}

package ltd.newbee.mall.service;

import ltd.newbee.mall.entity.Goods;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

import java.util.List;

public interface GoodsService {
//    分页查询
    PageResult getGoodsPage(PageQueryUtil pageUtil);

//    修改售卖状态
    Boolean batchUpdateSellStatus(Long[] ids, int sellStatus);

//    获取详情
    Goods getGoodsById(Long id);

//     商品搜索
    PageResult searchGoods(PageQueryUtil pageUtil);
}

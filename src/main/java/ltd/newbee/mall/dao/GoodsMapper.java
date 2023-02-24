package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.Goods;
import ltd.newbee.mall.entity.StockNumDTO;
import ltd.newbee.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    //    分页查询
    List<Goods> findGoodsList(PageQueryUtil pageUtil);

    // 总记录数
    int getTotalGoods(PageQueryUtil pageUtil);

    // 主键查询
    List<Goods> selectByPrimaryKeys(List<Long> goodsIds);

    // 修改售卖状态
    int batchUpdateSellStatus(@Param("orderIds")Long[] orderIds,@Param("sellStatus") int sellStatus);

    // 主键查询
    Goods selectByPrimaryKey(Long goodsId);


    List<Goods> findGoodsListBySearch(PageQueryUtil pageUtil);

    int getTotalGoodsBySearch(PageQueryUtil pageUtil);

    int updateStockNum(@Param("stockNumDTOS") List<StockNumDTO> stockNumDTOS);
}

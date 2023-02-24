package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.Order;
import ltd.newbee.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    //    分页查询订单列表
    List<Order> findOrderList(PageQueryUtil pageUtil);

    //    获取订单总数
    int getTotalOrders(PageQueryUtil pageUtil);

    //    修改订单
    int updateByPrimaryKey(Order record);

    //    主键查询订单
    int updateByPrimaryKeySelective(Order record);

    //    主键查询订单
    Order selectByPrimaryKey(Long orderId);

    //    配货完成
    int checkDone(@Param("orderIds") List<Long> asList);
    List<Order> selectByPrimaryKeys(@Param("orderIds") List<Long> orderIds);

    //    出库
    int checkOut(@Param("orderIds") List<Long> orderIds);

    //    关闭订单
    int closeOrder(@Param("orderIds") List<Long> orderIds, @Param("orderStatus") int orderStatus);

    Order selectByOrderNo(String orderNo);

    int insertSelective(Order record);
}

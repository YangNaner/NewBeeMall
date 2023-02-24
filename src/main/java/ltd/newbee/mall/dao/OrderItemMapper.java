package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemMapper {
    //   根据订单id获取订单项列表

    List<OrderItem> selectByOrderId(Long orderId);

    List<OrderItem> selectByOrderIds(@Param("orderIds") List<Long> orderIds);

    int insertBatch(@Param("orderItems") List<OrderItem> orderItems);
}

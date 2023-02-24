package ltd.newbee.mall.service;

import ltd.newbee.mall.controller.vo.OrderDetailVO;
import ltd.newbee.mall.controller.vo.OrderItemVO;
import ltd.newbee.mall.controller.vo.ShoppingCartItemVO;
import ltd.newbee.mall.controller.vo.UserVO;
import ltd.newbee.mall.entity.Order;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

import java.util.List;


public interface OrderService {
    //    分页查询列表
    PageResult getOrdersPage(PageQueryUtil pageUtil);

    //    修改订单
    String updateOrderInfo(Order order);

    //    配货完成
    String checkDone(Long[] ids);

    //    出库
    String checkOut(Long[] ids);

    //    关闭订单
    String closeOrder(Long[] ids);

    List<OrderItemVO> getOrderItems(Long id);

//    我的订单列表
    PageResult getMyOrders(PageQueryUtil pageUtil);

    OrderDetailVO getOrderDetailByOrderNo(String orderNo, Long userId);

    String saveOrder(UserVO user, List<ShoppingCartItemVO> myShoppingCartItems);

    String cancelOrder(String orderNo, Long userId);

    String finishOrder(String orderNo, Long userId);

    Order getOrderByOrderNo(String orderNo);

    String paySuccess(String orderNo, int payType);
}

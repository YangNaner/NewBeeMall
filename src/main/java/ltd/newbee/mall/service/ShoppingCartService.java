package ltd.newbee.mall.service;

import ltd.newbee.mall.controller.vo.ShoppingCartItemVO;
import ltd.newbee.mall.entity.ShoppingCartItem;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCartItemVO> getMyShoppingCartItems(Long newBeeMallUserId);

//    将商品存入购物车中
    String saveCartItem(ShoppingCartItem shoppingCartItem);

//    修改购物车中的属性
    String updateCartItem(ShoppingCartItem shoppingCartItem);

//    删除购物车中的商品
    Boolean deleteById(Long shoppingCartItemId);
}

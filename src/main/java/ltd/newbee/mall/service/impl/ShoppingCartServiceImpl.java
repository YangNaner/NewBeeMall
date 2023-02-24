package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.ShoppingCartItemVO;
import ltd.newbee.mall.dao.GoodsMapper;
import ltd.newbee.mall.dao.ShoppingCartItemMapper;
import ltd.newbee.mall.entity.Goods;
import ltd.newbee.mall.entity.ShoppingCartItem;
import ltd.newbee.mall.service.ShoppingCartService;
import ltd.newbee.mall.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

        @Autowired
        private ShoppingCartItemMapper shoppingCartItemMapper;

        @Autowired
        private GoodsMapper goodsMapper;

        @Override
        public List<ShoppingCartItemVO> getMyShoppingCartItems(Long newBeeMallUserId) {
                List<ShoppingCartItemVO> shoppingCartItemVOS = new ArrayList<>();
                List<ShoppingCartItem> shoppingCartItems = shoppingCartItemMapper.selectByUserId(newBeeMallUserId, Constants.SHOPPING_CART_ITEM_TOTAL_NUMBER);
                if (!CollectionUtils.isEmpty(shoppingCartItems)) {
                        //查询商品信息并做数据转换
                        List<Long> newBeeMallGoodsIds = shoppingCartItems.stream().map(ShoppingCartItem::getGoodsId).collect(Collectors.toList());
                        List<Goods> newBeeMallGoods = goodsMapper.selectByPrimaryKeys(newBeeMallGoodsIds);
                        Map<Long, Goods> goodsMap = new HashMap<>();
                        if (!CollectionUtils.isEmpty(newBeeMallGoods)) {
                                goodsMap = newBeeMallGoods.stream().collect(Collectors.toMap(Goods::getGoodsId, Function.identity(), (entity1, entity2) -> entity1));
                        }
                        for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
                                ShoppingCartItemVO shoppingCartItemVO = new ShoppingCartItemVO();
                                BeanUtil.copyProperties(shoppingCartItem, shoppingCartItemVO);
                                if (goodsMap.containsKey(shoppingCartItem.getGoodsId())) {
                                        Goods newBeeMallGoodsTemp = goodsMap.get(shoppingCartItem.getGoodsId());
                                        shoppingCartItemVO.setGoodsCoverImg(newBeeMallGoodsTemp.getGoodsCoverImg());
                                        String goodsName = newBeeMallGoodsTemp.getGoodsName();
                                        // 字符串过长导致文字超出的问题
                                        if (goodsName.length() > 28) {
                                                goodsName = goodsName.substring(0, 28) + "...";
                                        }
                                        shoppingCartItemVO.setGoodsName(goodsName);
                                        shoppingCartItemVO.setSellingPrice(newBeeMallGoodsTemp.getSellingPrice());
                                        shoppingCartItemVOS.add(shoppingCartItemVO);
                                }
                        }
                }
                return shoppingCartItemVOS;
        }

        @Override
        public String saveCartItem(ShoppingCartItem shoppingCartItem) {
                ShoppingCartItem temp = shoppingCartItemMapper.selectByUserIdAndGoodsId(shoppingCartItem.getUserId(), shoppingCartItem.getGoodsId());
                if (temp != null) {
                        //已存在则修改该记录
                        //todo count = tempCount + 1
                        temp.setGoodsCount(shoppingCartItem.getGoodsCount());
                        return updateCartItem(temp);
                }
                Goods goods = goodsMapper.selectByPrimaryKey(shoppingCartItem.getGoodsId());
                //商品为空
                if (goods == null) {
                        return ServiceResultEnum.GOODS_NOT_EXIST.getResult();
                }
                int totalItem = shoppingCartItemMapper.selectCountByUserId(shoppingCartItem.getUserId()) + 1;
                //超出单个商品的最大数量
                if (shoppingCartItem.getGoodsCount() > Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER) {
                        return ServiceResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getResult();
                }
                //超出最大数量
                if (totalItem > Constants.SHOPPING_CART_ITEM_TOTAL_NUMBER) {
                        return ServiceResultEnum.SHOPPING_CART_ITEM_TOTAL_NUMBER_ERROR.getResult();
                }
                //保存记录
                if (shoppingCartItemMapper.insertSelective(shoppingCartItem) > 0) {
                        return ServiceResultEnum.SUCCESS.getResult();
                }
                return ServiceResultEnum.DB_ERROR.getResult();
        }

        @Override
        public String updateCartItem(ShoppingCartItem shoppingCartItem) {
                ShoppingCartItem shoppingCartItemUpdate = shoppingCartItemMapper.selectByPrimaryKey(shoppingCartItem.getCartItemId());
                if (shoppingCartItemUpdate == null) {
                        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
                }
                //超出单个商品的最大数量
                if (shoppingCartItem.getGoodsCount() > Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER) {
                        return ServiceResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getResult();
                }
                //todo 数量相同不会进行修改
                //todo userId不同不能修改
                shoppingCartItemUpdate.setGoodsCount(shoppingCartItem.getGoodsCount());
                shoppingCartItemUpdate.setUpdateTime(new Date());
                //修改记录
                if (shoppingCartItemMapper.updateByPrimaryKeySelective(shoppingCartItemUpdate) > 0) {
                        return ServiceResultEnum.SUCCESS.getResult();
                }
                return ServiceResultEnum.DB_ERROR.getResult();
        }

        @Override
        public Boolean deleteById(Long shoppingCartItemId) {
                //todo userId不同不能删除
                return shoppingCartItemMapper.deleteByPrimaryKey(shoppingCartItemId) > 0;
        }
}

package ltd.newbee.mall.service;

import ltd.newbee.mall.controller.vo.IndexCarouselVO;
import ltd.newbee.mall.entity.Carousel;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

import java.util.List;

public interface CarouselService {
    //分页图
    PageResult getCarouselPage(PageQueryUtil pageQueryUtil);
    //添加轮播图
    String insertSelective(Carousel carousel);
    //修改轮播图
    String updateCarousel(Carousel carousel);
    //获取轮播图详情
    Carousel getCarouselById(Integer id);
    //批量删除轮播图
    Boolean deleteBatch(Integer[] ids);

    List<IndexCarouselVO> getCarouselsForIndex(int number);
}

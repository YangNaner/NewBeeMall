package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.Carousel;
import ltd.newbee.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarouselMapper {
    //查询分页数据
    List<Carousel> findCarouselList(PageQueryUtil pageQueryUtil);

    //查询总数
    int getTotalCarousels(PageQueryUtil pageQueryUtil);

    //轮播图新增
    int insertSelective(Carousel carousel);

    //轮播图修改
    int updateByPrimaryKeySelective(Carousel carousel);

    //轮播图详情
    Carousel selectByPrimaryKey(Integer carouselId);

    //轮播图删除
    int deleteBatch(Integer[] ids);

    List<Carousel> findCarouselsByNum(@Param("number") int number);
}

package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.IndexConfig;
import ltd.newbee.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IndexConfigMapper {
    //查询分页数据
    List<IndexConfig> findIndexConfigList(PageQueryUtil pageQueryUtil);

    //查询总数
    int getTotalIndexConfigs(PageQueryUtil pageQueryUtil);

    //新增
    int insertSelective(IndexConfig indexConfig);

    //修改
    int updateByPrimaryKeySelective(IndexConfig indexConfig);

    //详情
    IndexConfig selectByPrimaryKey(Long configId);

    //删除
    int deleteBatch(Long[] ids);

    List<IndexConfig> findIndexConfigsByTypeAndNum(@Param("configType") int configType, @Param("number") int number);
}

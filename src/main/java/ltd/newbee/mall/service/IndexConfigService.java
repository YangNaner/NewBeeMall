package ltd.newbee.mall.service;

import ltd.newbee.mall.controller.vo.IndexConfigGoodsVO;
import ltd.newbee.mall.entity.IndexConfig;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

import java.util.List;

public interface IndexConfigService {
//    配置页分页数据
    PageResult getConfigsPage(PageQueryUtil pageUtil);

//    新增配置页
    String saveIndexConfig(IndexConfig indexConfig);

//    修改配置页
    String updateIndexConfig(IndexConfig indexConfig);

//    删除配置页
    Boolean deleteBatch(Long[] ids);

    List<IndexConfigGoodsVO> getConfigGoodsesForIndex(int configType, int number);
}

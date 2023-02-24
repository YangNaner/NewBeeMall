package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.User;
import ltd.newbee.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    //    分页查询
    List<User> findUserList(PageQueryUtil pageUtil);

    // 总记录数
    int getTotalUsers(PageQueryUtil pageUtil);

    // 封禁
    int lockUserBatch(@Param("ids") Integer[] ids, @Param("lockStatus") int lockStatus);

//    通过用户名密码查询
    User selectByLoginNameAndPasswd(@Param("loginName") String loginName, @Param("password") String password);
//    通过用户名查询
    User selectByLoginName(String loginName);
//    注册
    int insertSelective(User record);
//    通过主键查询
    User selectByPrimaryKey(Long userId);
//    修改用户信息
    int updateByPrimaryKeySelective(User record);
}

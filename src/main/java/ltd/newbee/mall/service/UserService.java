package ltd.newbee.mall.service;

import ltd.newbee.mall.controller.vo.UserVO;
import ltd.newbee.mall.entity.User;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

import javax.servlet.http.HttpSession;

public interface UserService {
    //    分页查询
    PageResult getUsersPage(PageQueryUtil pageUtil);
    // 封禁
    Boolean lockUserBatch(Integer[] ids, int lockStatus);
//    登录
    String login(String loginName, String passwordMD5, HttpSession httpSession);
//    注册
    String register(String loginName, String password);
//    修改用户信息
    UserVO updateUserInfo(User mallUser, HttpSession httpSession);

}

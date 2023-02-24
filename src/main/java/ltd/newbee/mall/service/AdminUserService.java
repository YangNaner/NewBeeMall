package ltd.newbee.mall.service;

import ltd.newbee.mall.entity.AdminUser;

public interface AdminUserService {
    AdminUser getUserDetailById(Integer loginUserId);

    //    根据用户id，获取用户信息
    AdminUser login(String userName, String password);

//    修改用户信息
    Boolean updateName(Integer loginUserId,String loginUserName,String nickName);

//    修改用户密码
    Boolean updatePassword(Integer loginUserId,String originalPassword, String NewPassword);
}

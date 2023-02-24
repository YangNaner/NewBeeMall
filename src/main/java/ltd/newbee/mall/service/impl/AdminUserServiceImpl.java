package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.dao.AdminUserMapper;
import ltd.newbee.mall.entity.AdminUser;
import ltd.newbee.mall.service.AdminUserService;
import ltd.newbee.mall.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Override
    public AdminUser login(String userName, String password) {
        String md5Password = MD5Util.MD5Encode(password, "UTF-8");
        return adminUserMapper.login(userName, md5Password);
    }

    @Override
    public AdminUser getUserDetailById(Integer loginUserId) {
        AdminUser adminUser = adminUserMapper.selectByPrimarykey(loginUserId);
        System.out.println("登录用户: "+adminUser.getLoginUserName());
        return adminUserMapper.selectByPrimarykey(loginUserId);
    }

    //修改用户信息
    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        AdminUser adminUser = adminUserMapper.selectByPrimarykey(loginUserId);
        if(adminUser !=null){
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);
            if(adminUserMapper.updateByPrimarykeySelective(adminUser) > 0){
                return true;
            }
        }
        return false;
    }

    //修改用户密码
    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String NewPassword) {
        AdminUser adminUser = adminUserMapper.selectByPrimarykey(loginUserId);
        if(adminUser !=null){
            String originalPasswordMD5 = MD5Util.MD5Encode(originalPassword, "UTF-8");
            String NewPasswordMD5 = MD5Util.MD5Encode(NewPassword, "UTF-8");
            if(originalPasswordMD5.equals(adminUser.getLoginPassword())){
                adminUser.setLoginPassword(NewPasswordMD5);
                if(adminUserMapper.updateByPrimarykey(adminUser) > 0){
                    return true;
                }
            }
        }
        return false;
    }
}

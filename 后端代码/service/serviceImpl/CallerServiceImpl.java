package com.springboot.springbootlogindemo.service.serviceImpl;

import com.springboot.springbootlogindemo.domain.Caller;
import com.springboot.springbootlogindemo.repository.CallerDao;
import com.springboot.springbootlogindemo.service.CallerService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
@Service
public class CallerServiceImpl implements CallerService {
    @Resource
    private CallerDao callerDao;
    @Override
    public Caller loginService(String uname, String text) {
        // 如果账号密码都对则返回登录的用户对象，若有一个错误则返回null
        Caller user = callerDao.findByUnameAndText(uname, text);
        // 重要信息置空
        if(user != null){
            user.setText("");
        }
        return user;
    }
    @Override
    public Caller registService(Caller caller) {
        //原先的代码会实现不许用户名重复，所以这里进行修改，只要提交就保存
        //返回创建好的用户对象(带uid)
        Caller newUser = callerDao.save(caller);
        if(newUser != null){
            newUser.setText("");
        }
        return newUser;

        /*//当新用户的用户名已存在时
        if(callerDao.findByUname(caller.getUname())!=null){
            // 无法注册
            return null;
        }else{
            //返回创建好的用户对象(带uid)
            Caller newUser = callerDao.save(caller);
            if(newUser != null){
                newUser.setText("");
            }
            return newUser;
        }*/
    }
}

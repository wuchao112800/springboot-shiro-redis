package com.example.demo.Service;




import com.example.demo.Dao.UserDao;
import com.example.demo.Model.User;
import com.example.demo.Util.BSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceDetail {

    @Autowired
    private UserDao userRepository;

    public User findUserById(int id){
        return  userRepository.findById(Long.parseLong(id+"")).get();
    }

    public User findByUsername(String username){
        return  userRepository.findByUsername(username);
    }

    public User findByUsernameAndPassword(String username,String password)   {
        return userRepository.findByUsernameAndPassword(username,password);
    }

    public List<User> selectAllUser(){
        return  userRepository.findAll();
    }

    @Transactional
    public void saveUser(User user){
        userRepository.save(user);
    }

    @Transactional
    public void  deleteUserByID(Long id){
        userRepository.deleteById(id);
    }

    @Transactional
    public void test(User user){
        userRepository.save(user);
        System.out.println("---------------");
        BSUtil.isTrue(false, "狗狗名字已经被使用了...");
    }


}

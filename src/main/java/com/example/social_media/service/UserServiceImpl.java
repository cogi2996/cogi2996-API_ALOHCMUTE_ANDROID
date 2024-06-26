package com.example.social_media.service;

import com.example.social_media.dao.UserRepository;
import com.example.social_media.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final  UserRepository userRepository;
    private final AccountService accountService;

    @Override
    public User findUserById(int userId) {
        return userRepository.findUserByUserId(userId);
    }

    @Override
    public Page<User> findAll(int pageNum, int pageSize, String sortBy) {
        Pageable paging;
        if(sortBy == null){
            paging = PageRequest.of(pageNum, pageSize);
            return userRepository.findAll(paging);
        }

        paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
        return userRepository.findAll(paging);
    }

    @Override
    public int countAll() {
        return userRepository.countAll();
    }


    @Override
    public List<User> findFollowersByUserId(int userid,int pageNum, int pageSize, String sortBy) {
        return userRepository.findFollowersByUserId(userid,PageRequest.of(pageNum,pageSize,Sort.by(sortBy)));
    }

    @Override
    public List<User> findFollowingUserByUserId(int userId, int pageNum, int pageSize, String sortBy) {
        return userRepository.findFollowingUserByUserId(userId,PageRequest.of(pageNum,pageSize,Sort.by(sortBy)));
    }


    @Override
    public List<User> findPeopleNotFollowedByUserId(int userId,int pageNum, int pageSize) {
        Pageable paging = PageRequest.of(pageNum,pageSize);
        return userRepository.findPeopleNotFollowedByUserId(userId,paging);
    }

    @Override
    public User update(User user) {
        User dbuser = userRepository.findUserByUserId(user.getUserId());
        if (dbuser == null) {
            return null;
        }

        dbuser.setFirstName(user.getFirstName());
        dbuser.setLastName(user.getLastName());
        dbuser.setMidName(user.getMidName());
        if(user.getAvatar() != null)
        {
            dbuser.setAvatar(user.getAvatar());
        }
        dbuser.setDob(user.getDob());
        dbuser.setGender(user.getGender());
        dbuser.setDepartment(user.getDepartment());
        dbuser.setMajor(user.getMajor());
        dbuser.setAddress(user.getAddress());
        return userRepository.save(dbuser);
    }

    @Override
    public List<User> searchUserByName(String name, int pageNum, int pageSize, Sort sort) {
        if (sort == null) {
            return userRepository.searchUserByName(name, PageRequest.of(pageNum, pageSize));
        }
        return userRepository.searchUserByName(name,PageRequest.of(pageNum,pageSize,sort));
    }

    @Override
    public List<User> findPendingFollowingById(int userId, int pageNum, int pageSize, Sort sort) {
        if(sort == null)
            return userRepository.findPendingFollowingById(userId,PageRequest.of(pageNum,pageSize));
        return userRepository.findPendingFollowingById(userId,PageRequest.of(pageNum,pageSize,sort));
    }

    @Override
    public void delete(int userId) {
        // xoá user
        userRepository.deleteById(userId);
    }

}

package com.example.social_media.service;

import com.example.social_media.dao.GroupRepository;
import com.example.social_media.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;

public class GroupServiceImpl implements GroupService{
    @Autowired
    private GroupRepository groupRepository;
    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void deleteGroupByGroupId(int groupId) {
        groupRepository.deleteById(groupId);
    }
}

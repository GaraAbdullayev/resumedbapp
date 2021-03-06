/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.User;
import com.company.entity.UserSkill;

import java.util.List;

/**
 *
 * @author Qara
 */
public interface UserDaoInter {

    public List<User> getAll();
    public User getById(int userId);
    public boolean addUser(User u);
    public boolean update(User u);
    public boolean removeUser(int id);
    public List<UserSkill> getAllSkillByUserId(int userID);
}

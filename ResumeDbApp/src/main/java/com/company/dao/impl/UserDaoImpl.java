/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Country;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;
import com.company.dao.inter.UserDaoInter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.company.dao.inter.AbstractDao;
import java.sql.Date;
import java.sql.PreparedStatement;

/**
 *
 * @author Qara
 */
public class UserDaoImpl extends AbstractDao implements UserDaoInter {

    public User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        Date birthDate = rs.getDate("birthdate");
        int nationalityId = rs.getInt("nationality_id");
        int birhtPlaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthPlaceStr = rs.getString("birthplace");
        String profileDesc = rs.getString("profile_description");
        Country nationality = new Country(nationalityId, nationalityStr, null);
        Country birthPlace = new Country(birhtPlaceId, null, birthPlaceStr);
        return new User(id, name, surname, phone, email, profileDesc, birthDate, nationality, birthPlace);
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();

        try (Connection c = connect()) {

            Statement stmt = c.createStatement();//db-e sorgu gondermek ucun
            stmt.execute("SELECT u.*, "
                    + "n.nationality nationalityas , "
                    + "c.name as birthplace "
                    + "FROM USER AS u "
                    + "LEFT JOIN country AS n ON u.nationality_id = n.id "
                    + "LEFT JOIN country AS c ON u.birthplace_id = c.id");//iceride yazilan sorgunu db-ye gonderir
            ResultSet rs = stmt.getResultSet();//bazanin verdiyi neticeni alir.

            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;

    }

    @Override
    public boolean update(User u) {

        try (Connection c = connect()) {

            PreparedStatement pstmt = c.prepareStatement("UPDATE USER u "
                    + "SET NAME = ? , "
                    + "surname=? , "
                    + "profile_description=? , "
                    + "email=? , "
                    + "phone=? , "
                    + "birthdate=? "
                    + "where u.id=? ");
            pstmt.setString(1, u.getName());
            pstmt.setString(2, u.getSurname());
            pstmt.setString(3, u.getProfileDesc());
            pstmt.setString(4,u.getEmail());
            pstmt.setString(5, u.getPhone());
            pstmt.setDate(6, u.getBirthDate());
            pstmt.setInt(7, u.getId());
            return pstmt.execute();
             
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from  user where id" + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public UserSkill getUserSkill(ResultSet rs) throws Exception {
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");

        return new UserSkill(userId, new User(userId), new Skill(skillId, skillName), power);
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("SELECT "
                    + "us.*,u.name,s.skill_name "
                    + "FROM "
                    + "user_skill AS us "
                    + "LEFT JOIN USER u ON us.user_id = u.id "
                    + "LEFT JOIN skill s ON us.skill_id = s.id "
                    + "WHERE "
                    + "us.user_id = ?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill us = getUserSkill(rs);
                result.add(us);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT "
                    + "	u.*,"
                    + "	n.nationality as nationality,"
                    + "	c.name as birthplace "
                    + "FROM "
                    + "	USER AS u "
                    + "	LEFT JOIN country AS n ON u.nationality_id = n.id "
                    + "	LEFT JOIN country AS c ON u.birthplace_id = c.id where u.id=?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result = getUser(rs);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement pstmt = c.prepareStatement("insert into user(name,surname,phone,email) values(?,?,?,?)");
            pstmt.setString(1, u.getName());
            pstmt.setString(2, u.getSurname());
            pstmt.setString(3, u.getPhone());
            pstmt.setString(4, u.getEmail());
            return pstmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}

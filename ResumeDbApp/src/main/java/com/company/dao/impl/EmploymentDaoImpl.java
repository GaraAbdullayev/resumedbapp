package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.EmploymentDaoInter;
import com.company.entity.EmploymentHistory;
import com.company.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmploymentDaoImpl extends AbstractDao implements EmploymentDaoInter {

    @Override
    public EmploymentHistory getInfo(ResultSet rs) throws Exception {
        //SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
        int id = rs.getInt("id");
        String header = rs.getString("header");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        int userId = rs.getInt("user_id");

        return new EmploymentHistory(id, header, beginDate, endDate, new User(userId));
    }

    @Override
    public List<EmploymentHistory> getUserInfo(int userId) {
        List<EmploymentHistory> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement psmt = c.prepareStatement("select * from employment_history where user_id=?");
            psmt.setInt(1, userId);
            psmt.execute();
            ResultSet rs = psmt.getResultSet();
            while (rs.next()) {
                EmploymentHistory eh = getInfo(rs);
                result.add(eh);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}

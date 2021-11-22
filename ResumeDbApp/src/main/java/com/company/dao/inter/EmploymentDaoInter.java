package com.company.dao.inter;

import com.company.entity.EmploymentHistory;

import java.sql.ResultSet;
import java.util.List;

public interface EmploymentDaoInter {
    public EmploymentHistory getInfo(ResultSet rs) throws Exception;
    public List<EmploymentHistory> getUserInfo(int userId);
}

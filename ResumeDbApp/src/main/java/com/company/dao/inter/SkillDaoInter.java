package com.company.dao.inter;


import com.company.entity.Skill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface SkillDaoInter {
    public Skill getSkill(ResultSet rs) throws SQLException, Exception;
    public List<Skill> getAllSkill();
}

package main;

import com.company.dao.impl.CountryDaoImpl;
import com.company.dao.impl.SkillDaoImpl;
import com.company.dao.impl.UserDaoImpl;
import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.SkillDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.Country;
import com.company.entity.Skill;

public class Context {
    public static UserDaoInter userInstance(){
        return new UserDaoImpl();
    }
    public static CountryDaoInter countryInstance(){
        return new CountryDaoImpl();
    }
    public static SkillDaoInter skillInstance(){
        return new SkillDaoImpl();
    }
}

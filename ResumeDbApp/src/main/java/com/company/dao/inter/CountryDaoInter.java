package com.company.dao.inter;

import com.company.entity.Country;

import java.sql.ResultSet;
import java.util.List;

public interface CountryDaoInter{
    public Country getCountry(ResultSet rs) throws Exception;
    public List<Country> getAllCountry();
}

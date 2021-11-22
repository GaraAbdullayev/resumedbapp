package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDao implements CountryDaoInter{


    @Override
    public Country getCountry(ResultSet rs) throws Exception{
        int id = rs.getInt("id");
        String nationality=rs.getString("nationality");
        String name=rs.getString("name");
        return new Country(id,nationality,name);
    }

    @Override
    public List<Country> getAllCountry() {
        List<Country> result=new ArrayList<>();

        try(Connection c=connect()){
            Statement stmt=c.createStatement();
            stmt.execute("select * from country");

            ResultSet rs= stmt.getResultSet();
            while(rs.next()){
                Country con=getCountry(rs);
                result.add(con);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
}


import com.company.dao.impl.CountryDaoImpl;
import com.company.dao.impl.UserDaoImpl;
import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.UserDaoInter;
import main.Context;

public class TestDrive {

    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

    /**
     *
     * @author Qara
     */
    public static void main(String[] args) throws Exception {
       Context t= new Context();
        System.out.println(t.skillInstance().getAllSkill());


    }

}

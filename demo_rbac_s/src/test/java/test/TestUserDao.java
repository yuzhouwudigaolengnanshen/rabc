package test;

import com.westos.rbac.dao.ModuleDao;
import com.westos.rbac.dao.UserDao;
import com.westos.rbac.dao.impl.ModuleDaoImpl;
import com.westos.rbac.dao.impl.UserDaoImpl;
import com.westos.rbac.domain.Module;
import com.westos.rbac.domain.User;
import org.junit.Test;

import java.util.List;

public class TestUserDao {
    private UserDao userDao = new UserDaoImpl();

    @Test
    public void test1() {
        List<User> list = userDao.findByPage(3, 5);
        for (User user : list) {
            System.out.println(user);
        }
    }


    @Test
    public void test2(){
         ModuleDao moduleDao  = new ModuleDaoImpl();
        List<Module> all = moduleDao.findAll();
        System.out.println(all);
        System.out.println("---------------");
        List<Module> roleId = moduleDao.findByRoleId(1);
        System.out.println(roleId);
    }
}

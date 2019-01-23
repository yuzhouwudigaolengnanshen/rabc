package test;

import com.westos.rbac.dao.UserDao;
import com.westos.rbac.dao.impl.UserDaoImpl;
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
}

package test;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.junit.Test;

public class TestBCrypt {

    @Test
    public void test1() {
        String s = BCrypt.withDefaults().hashToString(12, "123456".toCharArray());
        System.out.println(s + "-" + s.length());
        System.out.println(BCrypt.verifyer().verify("123456".toCharArray(), s));

        s = BCrypt.withDefaults().hashToString(12, "123456".toCharArray());
        System.out.println(s + "-" + s.length());
        System.out.println(BCrypt.verifyer().verify("123456".toCharArray(), s));

        s = BCrypt.withDefaults().hashToString(12, "1234567".toCharArray());
        System.out.println(s + "-" + s.length());
        System.out.println(BCrypt.verifyer().verify("1234567".toCharArray(), s));


    }
}

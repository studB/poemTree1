package practice.poemtree1.logintest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import practice.poemtree1.login.Login;
import practice.poemtree1.login.LoginConfig;
import practice.poemtree1.login.Member;

@SpringBootApplication
@Transactional
public class LoginTest {
    
    ApplicationContext ac = new AnnotationConfigApplicationContext(LoginConfig.class);
    Login login = ac.getBean("login", Login.class);


    @Test
    void loginTest(){

        // Test of creation
        Member member = new Member();
        member.setName("testman");
        member.setPassword("bdytest1234");

        Boolean createResult = login.memberCreate(member);
        Assertions.assertThat(createResult).isTrue();

        // Test of login
        Member loginUser = new Member();
        loginUser.setName("testman");
        loginUser.setPassword("bdytest1234");

        Boolean loginResult = login.authCheck(loginUser);
        Assertions.assertThat(loginResult).isTrue();

        // Test of deletion
        Boolean deleteResult = login.memberDelete(member);
        Assertions.assertThat(deleteResult).isTrue();
    }




}

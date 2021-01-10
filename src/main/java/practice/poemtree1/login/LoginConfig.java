package practice.poemtree1.login;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import practice.poemtree1.db.DBConfig;
import practice.poemtree1.db.MemberDB;

@Configuration
public class LoginConfig {

    ApplicationContext ac = new AnnotationConfigApplicationContext(DBConfig.class);
    MemberDB memberDB = ac.getBean("memberDB", MemberDB.class);

    @Bean
    public Login login(){
        return new LoginImpl(memberDB);
    }
    
}

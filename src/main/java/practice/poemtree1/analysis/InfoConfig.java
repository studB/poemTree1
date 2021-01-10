package practice.poemtree1.analysis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import practice.poemtree1.db.DBConfig;
import practice.poemtree1.db.MemberInfoDB;

@Configuration
public class InfoConfig {
    
    ApplicationContext ac = new AnnotationConfigApplicationContext(DBConfig.class);
    MemberInfoDB memberInfoDB = ac.getBean("memberInfoDB", MemberInfoDB.class);

    @Bean
    public InfoAdminister infoAdminister(){
        return new InfoAdministerImpl(memberInfoDB);
    }
}

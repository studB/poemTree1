package practice.poemtree1.content;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import practice.poemtree1.db.ContentDB;
import practice.poemtree1.db.DBConfig;

@Configuration
public class ContentConfig {
    
    ApplicationContext ac = new AnnotationConfigApplicationContext(DBConfig.class);
    ContentDB contentDB = ac.getBean("contentDB", ContentDB.class);

    @Bean
    public ContentAdminister contentAdminister(){
        return new ContentAdministerImpl(contentDB);
    }
}

package practice.poemtree1.contenttest;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import practice.poemtree1.content.Content;
import practice.poemtree1.content.ContentAdminister;
import practice.poemtree1.content.ContentConfig;

@SpringBootTest
public class ContentAdministerTest {
    
    ApplicationContext ac = new AnnotationConfigApplicationContext(ContentConfig.class);
    ContentAdminister ca = ac.getBean("contentAdminister", ContentAdminister.class);

    @Test
    void contentAdministerTest(){
        
        String pid = UUID.randomUUID().toString();

        Content content = new Content();
        content.setPid(pid);
        content.setTitle("title");
        content.setPoem("poem");

        ca.createContent(content);

        Assertions.assertThat(ca.contentList(pid).get(0)).isEqualTo("title");

        Assertions.assertThat(ca.loadPoem("title")).isNotEmpty();


    }

}

package practice.poemtree1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import practice.poemtree1.db.DBConfig;

@SpringBootTest
class Poemtree1ApplicationTests {

	ApplicationContext ac = new AnnotationConfigApplicationContext(DBConfig.class);

	@Test
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
        Assertions.assertThat(1).isEqualTo(1);
    }
}

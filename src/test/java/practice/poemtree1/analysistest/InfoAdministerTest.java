package practice.poemtree1.analysistest;

import java.util.List;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import practice.poemtree1.analysis.InfoAdminister;
import practice.poemtree1.analysis.InfoConfig;
import practice.poemtree1.analysis.MemberInfo;

@SpringBootTest
@Transactional
public class InfoAdministerTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(InfoConfig.class);
    InfoAdminister ia = ac.getBean("infoAdminister", InfoAdminister.class);

    @Test
    void infoAdministerTest(){

        String pid = UUID.randomUUID().toString();

        MemberInfo mi = new MemberInfo();
        mi.setPid(pid);
        mi.setName("testman");
        mi.setFavor("poem");
        mi.setAge(20);

        List<String> result1 = ia.getAges();

        ia.infoResisger(mi);
        Assertions.assertThat(ia.infoGet(pid).getPid()).isEqualTo(pid);

        List<String> result2 = ia.getAges();

        Assertions.assertThat(result2.size()).isGreaterThan(result1.size());

        Boolean deleteResult = ia.infoDelete(mi);
        Assertions.assertThat(deleteResult).isTrue();
    }
}

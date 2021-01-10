package practice.poemtree1.DBConntectTest;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import practice.poemtree1.analysis.MemberInfo;
import practice.poemtree1.content.Content;
import practice.poemtree1.db.ContentDB;
import practice.poemtree1.db.DBConfig;
import practice.poemtree1.db.MemberDB;
import practice.poemtree1.db.MemberInfoDB;
import practice.poemtree1.login.Member;

@SpringBootTest
@Transactional
public class DBTest {
    /*
    @Autowired
    private MemberDB memberDB;

    @Test
    void dbOperation() {

        Member testman = new Member();
        testman.setName("testman");
        testman.setPassword("bdytest1234");
        memberDB.insertRow(testman);

        Assertions.assertThat(memberDB.findByName("testman")).isNotEmpty();

    }
    */

    ApplicationContext ac = new AnnotationConfigApplicationContext(DBConfig.class);

    @Test
    void MemberdbOperation() {

        MemberDB memberDB = ac.getBean("memberDB", MemberDB.class);

        Member testman = new Member();
        testman.setName("testman");
        testman.setPassword("bdytest1234");
        memberDB.insertRow(testman);

        Assertions.assertThat(memberDB.findByName("testman")).isNotEmpty();

        memberDB.deleteRow(testman);

        Assertions.assertThat(memberDB.findByName("testman")).isEmpty();

    }

    @Test
    void MemberinfodbOperation() {

        MemberInfoDB memberInfoDB = ac.getBean("memberInfoDB", MemberInfoDB.class);

        MemberInfo testinfo = new MemberInfo();
        testinfo.setPid("1");
        testinfo.setName("testman");
        testinfo.setAge(20);
        testinfo.setFavor("poem");

        memberInfoDB.insertRow(testinfo);

        Assertions.assertThat(memberInfoDB.findByPID("1")).isNotEmpty();

        memberInfoDB.deleteRow("1");

        Assertions.assertThat(memberInfoDB.findByName("testman")).isEmpty();

    }

    @Test
    void MemberinfoColumGet(){

        MemberInfoDB memberInfoDB = ac.getBean("memberInfoDB", MemberInfoDB.class);

        MemberInfo test1 = new MemberInfo();
        test1.setPid("1");
        test1.setName("testman");
        test1.setAge(20);
        test1.setFavor("poem");
        memberInfoDB.insertRow(test1);

        MemberInfo test2 = new MemberInfo();
        test2.setPid("2");
        test2.setName("testman2");
        test2.setAge(30);
        test2.setFavor("poem");
        memberInfoDB.insertRow(test2);

        List<String> result = memberInfoDB.getColumnAge();

        Assertions.assertThat(result.get(0)).isEqualTo("20");
    }

    @Test
    void contentdbOperation() {

        ContentDB contentDB = ac.getBean("contentDB", ContentDB.class);

        Content content = new Content();
        content.setPnum(10);
        content.setPid("1");
        content.setTitle("title");
        content.setPoem("poem");

        contentDB.insertRow(content);

        Assertions.assertThat(contentDB.findByPID("1")).isNotEmpty();

        contentDB.deleteRow(10);

        Assertions.assertThat(contentDB.findByTitle("title")).isEmpty();

    }
}

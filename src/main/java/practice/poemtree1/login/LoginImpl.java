package practice.poemtree1.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import practice.poemtree1.db.MemberDB;

public class LoginImpl implements Login{

    /*
    ApplicationContext ac = new AnnotationConfigApplicationContext(DBConfig.class);
    MemberDB memberDB = ac.getBean("memberDB", MemberDB.class);
    */

    MemberDB memberDB;

    @Autowired
    public LoginImpl(MemberDB memberDB){
        this.memberDB = memberDB;
    }

    @Override
    public Boolean authCheck(Member member) {
    
        String name = member.getName();
        System.out.println(name);
        Optional<Member> presentMember = memberDB.findByName(name);
        
        // Check unique ID
        if (presentMember.isEmpty()){
            return false;
        } else if (presentMember.get().getPassword().equals(member.getPassword())){
            return true;
        }
        return false;

    }

    @Override
    public Boolean memberCreate(Member member) {
        if(duplicateCheck(member)){
            return false;
        }else{
            memberDB.insertRow(member);
            return true;
        }
    }

    @Override
    public Boolean memberDelete(Member member) {
        if(duplicateCheck(member)){
            memberDB.deleteRow(member);
            return true;
        }else{
            return false;
        }
    }

    private boolean duplicateCheck(Member member){
        String name = member.getName();
        Optional<Member> presentMember = memberDB.findByName(name);
        if (presentMember.isEmpty()){
            return false;
        } else{
            return true;
        }
    }
    
}

package practice.poemtree1.analysis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import practice.poemtree1.db.MemberInfoDB;

public class InfoAdministerImpl implements InfoAdminister {

    MemberInfoDB memberInfoDB;

    @Autowired
    public InfoAdministerImpl(MemberInfoDB memberInfoDB){
        this.memberInfoDB = memberInfoDB;
    }


    @Override
    public Boolean infoResisger(MemberInfo memberInfo) {
        memberInfoDB.insertRow(memberInfo);
        return true;
    }

    @Override
    public Boolean infoDelete(MemberInfo memberInfo) {
        memberInfoDB.deleteRow(memberInfo.getPid());
        return true;
    }

    @Override
    public MemberInfo infoGet(String pid) {
        Optional<MemberInfo> result = memberInfoDB.findByPID(pid);
        return result.get();
    }

    @Override
    public List<String> getAges() {
        List<String> result = memberInfoDB.getColumnAge();
        return result;
    }
    
}

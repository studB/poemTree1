package practice.poemtree1.analysis;

import java.util.List;

public interface InfoAdminister {
    
    Boolean infoResisger(MemberInfo memberInfo);

    Boolean infoDelete(MemberInfo memberInfo);

    MemberInfo infoGet(String pid);

    List<String> getAges();

	String findPIDByName(String name);

}

package practice.poemtree1.db;

import java.util.List;
import java.util.Optional;

import practice.poemtree1.analysis.MemberInfo;

public interface MemberInfoDB {
    
    void insertRow(MemberInfo memberInfo);

    void deleteRow(String pid);

    void updateRow(MemberInfo memberInfo);

    Optional<MemberInfo> findByPID(String pid);

    Optional<MemberInfo> findByName(String name);

    List<String> getColumnAge();
}

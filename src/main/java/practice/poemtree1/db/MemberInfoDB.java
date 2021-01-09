package practice.poemtree1.db;

import java.util.Optional;

import practice.poemtree1.analysis.MemberInfo;

public interface MemberInfoDB {
    
    void insertRow(MemberInfo memberInfo);

    void deleteRow(int pid);

    void updateRow(MemberInfo memberInfo);

    Optional<MemberInfo> findByPID(int pid);

    Optional<MemberInfo> findByName(String name);
}

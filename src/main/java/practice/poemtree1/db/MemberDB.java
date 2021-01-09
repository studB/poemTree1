package practice.poemtree1.db;

import java.util.Optional;

import practice.poemtree1.login.Member;

public interface MemberDB {

    void insertRow(Member member);

    void deleteRow(Member member);

    Optional<Member> findByName(String name);

}

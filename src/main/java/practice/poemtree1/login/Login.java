package practice.poemtree1.login;

public interface Login {

    /*
     * @return : check result, true means pass
     */
    Boolean authCheck(Member member);

    void memberCreate(Member member);

    void memberDelete(Member member);

}

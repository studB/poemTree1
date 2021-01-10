package practice.poemtree1.login;

public interface Login {

    /*
     * @return : check result, true means pass
     */
    Boolean authCheck(Member member);

    Boolean memberCreate(Member member);

    Boolean memberDelete(Member member);

}

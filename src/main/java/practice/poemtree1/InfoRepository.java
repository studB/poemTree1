package practice.poemtree1;

import practice.poemtree1.analysis.InfoAdminister;

public class InfoRepository {

    private static InfoRepository infoRepository = new InfoRepository();

    String name;
    String pid;

    private InfoRepository() {
    };

    public static InfoRepository getInstance(){
        return infoRepository;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    };

    public String getPid() {
        return this.pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void clear(){
        this.pid = null;
        this.name = null;
    }

}

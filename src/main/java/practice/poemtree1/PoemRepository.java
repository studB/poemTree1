package practice.poemtree1;

import java.util.List;

import practice.poemtree1.content.Content;

public class PoemRepository {
    
    private static PoemRepository poemRepository = new PoemRepository();

    List<Content> poemInfo;

    private PoemRepository(){};

    public static PoemRepository getInstance(){
        return poemRepository;
    }

    public void setPR(List<Content> contents){
        this.poemInfo = contents;
    }

    public List<Content> getPR(){
        return this.poemInfo;
    }

    public int getIen(){
        return this.poemInfo.size();
    }
}


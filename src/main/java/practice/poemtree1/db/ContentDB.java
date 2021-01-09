package practice.poemtree1.db;

import java.util.List;
import java.util.Optional;

import practice.poemtree1.content.Content;

public interface ContentDB {

    void insertRow(Content content);

    void deleteRow(int pnum);

    /*
    * return content p-num;
    */
    int updateTitle(Content content);
    int updatePoem(Content content);

    List<Content> findByPID(int pid); 
    Optional<Content> findByTitle(String title);

}

package practice.poemtree1.content;

import java.util.List;
import java.util.Optional;

public interface ContentAdminister {
    
    Boolean createContent(Content content);

    Boolean deleteContent(int pnum);

    Boolean updateTitle(Content content);

    Boolean updatePoem(Content content);

    List<String> contentList(String pid);

    Optional<Content> loadPoem(String title);

}

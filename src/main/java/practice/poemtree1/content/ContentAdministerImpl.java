package practice.poemtree1.content;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import practice.poemtree1.db.ContentDB;

public class ContentAdministerImpl implements ContentAdminister {

    ContentDB contentDB;

    @Autowired
    public ContentAdministerImpl(ContentDB contentDB){
        this.contentDB = contentDB;
    }

    @Override
    public Boolean createContent(Content content) {
        contentDB.insertRow(content);
        return true;
    }

    @Override
    public Boolean deleteContent(int pnum) {
        contentDB.deleteRow(pnum);
        return true;
    }

    @Override
    public Boolean updateTitle(Content content) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Boolean updatePoem(Content content) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public List<String> contentList(String pid) {
        List<Content> result = contentDB.findByPID(pid);
        List<String> titleList = new ArrayList<String>();
        for(Content c : result){
            titleList.add(c.getTitle());
        }
        return titleList;
    }

    @Override
    public Optional<Content> loadPoem(String title) {
        Optional<Content> result = contentDB.findByTitle(title);
        return result.stream().findAny();
    }
    
}

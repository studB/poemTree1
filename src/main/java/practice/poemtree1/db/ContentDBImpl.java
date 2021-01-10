package practice.poemtree1.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import practice.poemtree1.content.Content;

public class ContentDBImpl implements ContentDB {


    /*
     * ContentDB CREATE SQL
     * 
     * create table content( pnum int auto_increment, pid varchar(255), title varchar(255), poem varchar(255))
     */

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ContentDBImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void insertRow(Content content) {
        jdbcTemplate.update("INSERT INTO CONTENT VALUES(?,?,?,?)", 
            content.getPnum(), content.getPid(), content.getTitle(), content.getPoem());

    }

    @Override
    public void deleteRow(int pnum) {
        jdbcTemplate.update("DELETE FROM CONTENT WHERE PNUM = ?", pnum);

    }

    @Override
    public int updateTitle(Content content) {
        // TODO Auto-generated method stub
        return content.getPnum();
    }

    @Override
    public int updatePoem(Content content) {
        // TODO Auto-generated method stub
        return content.getPnum();
    }

    @Override
    public List<Content> findByPID(String pid) {
        return jdbcTemplate.query("SELECT * FROM CONTENT WHERE PID = ?", contentRowMapper(), pid);
    }

    @Override
    public Optional<Content> findByTitle(String title) {
        List<Content> result = jdbcTemplate.query("SELECT * FROM CONTENT WHERE TITLE = ?", contentRowMapper(), title);
        return result.stream().findAny();
    }

    private RowMapper<Content> contentRowMapper(){
        return new RowMapper<Content> (){
            @Override
            public Content mapRow(ResultSet rs, int rowNum) throws SQLException{
                Content content = new Content();
                content.setPnum(rs.getInt("pnum"));
                content.setPid(rs.getString("pid"));
                content.setTitle(rs.getString("title"));
                content.setPoem(rs.getString("poem"));
                return content;
            }
        };
    }

    
}

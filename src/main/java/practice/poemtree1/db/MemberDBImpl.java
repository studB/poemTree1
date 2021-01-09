package practice.poemtree1.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import practice.poemtree1.login.Member;

@Repository
public class MemberDBImpl implements MemberDB {

    /*
     * MemberDB CREATE SQL
     * 
     * create table member ( id int primary key auto_increment, name varchar(255),
     * password varchar(255) )
     */

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberDBImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void insertRow(Member member) {
        jdbcTemplate.update("INSERT INTO MEMBER (NAME, PASSWORD) VALUES (?,?)", member.getName(), member.getPassword());
    }

    @Override
    public void deleteRow(Member member) {
        jdbcTemplate.update("DELETE FROM MEMBER WHERE NAME = ?", member.getName());
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("SELECT * FROM MEMBER WHERE NAME = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    private RowMapper<Member> memberRowMapper() {
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                member.setId(rs.getInt("id"));
                member.setName(rs.getString("name"));
                member.setPassword(rs.getString("password"));
                return member;
            }
        };
    }

}

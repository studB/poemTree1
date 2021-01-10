package practice.poemtree1.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import practice.poemtree1.analysis.MemberInfo;

public class MemberInfoDBImpl implements MemberInfoDB {

    /*
     * MemberInfoDB CREATE SQL
     * 
     * create table memberinfo ( pid varchar(255) unique, name varchar(255), age int, favor varchar(255))
     */

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberInfoDBImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void insertRow(MemberInfo memberInfo) {
        jdbcTemplate.update("INSERT INTO MEMBERINFO (PID, NAME, AGE, FAVOR) VALUES (?,?,?,?)",
                memberInfo.getPid(), memberInfo.getName(), memberInfo.getAge(), memberInfo.getFavor());
    }

    @Override
    public void deleteRow(String pid) {
        jdbcTemplate.update("DELETE FROM MEMBERINFO WHERE PID = ?", pid);
    }

    @Override
    public void updateRow(MemberInfo memberInfo) {
        // TODO Auto-generated method stub
    }

    @Override
    public Optional<MemberInfo> findByPID(String pid) {
        List<MemberInfo> result = jdbcTemplate.query("SELECT * FROM MEMBERINFO WHERE PID = ?", memberInfoRowMapper(), pid);
        return result.stream().findAny();
    }

    @Override
    public Optional<MemberInfo> findByName(String name) {
        List<MemberInfo> result = jdbcTemplate.query("SELECT * FROM MEMBERINFO WHERE NAME = ?", memberInfoRowMapper(), name);
        return result.stream().findAny();
    }

    private RowMapper<MemberInfo> memberInfoRowMapper(){
        return new RowMapper<MemberInfo>(){
            @Override
            public MemberInfo mapRow(ResultSet rs, int rowNum) throws SQLException{
                MemberInfo memberInfo = new MemberInfo();
                memberInfo.setPid(rs.getString("pid"));
                memberInfo.setName(rs.getString("name"));
                memberInfo.setAge(rs.getInt("age"));
                memberInfo.setFavor(rs.getString("favor"));
                return memberInfo;
            }
        };
    }

    @Override
    public List<String> getColumnAge() {
        String colname = "age";
        return jdbcTemplate.query("SELECT AGE FROM MEMBERINFO", colMapper(colname));
    }

    private RowMapper<String> colMapper(String colname){
        return new RowMapper<String>(){
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException{
                return rs.getString(colname);
            }
        };
    }
    
}

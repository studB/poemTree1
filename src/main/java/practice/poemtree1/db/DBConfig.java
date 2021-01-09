package practice.poemtree1.db;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {
    /*
    This method is capable by '@EnableAutoConfiguration' Annotation
    That make connection between application.properties file and jdbctemplate generaing impl of 'datasource'
    But on that method datasource bean isn't created so error rasie on constructing applicationcontext

    private final DataSource dataSource;

    @Autowired
    public DBConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    */

    @Bean
    public DataSource getDataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username("sa");
        dataSourceBuilder.url("jdbc:h2:~/poemtree1");
        dataSourceBuilder.driverClassName("org.h2.Driver");
        return dataSourceBuilder.build();
    }

    @Bean
    public MemberDB memberDB() {
        return new MemberDBImpl(getDataSource());
    }

    @Bean
    public MemberInfoDB memberInfoDB(){
        return new MemberInfoDBImpl(getDataSource());
    }

    @Bean
    public ContentDB contentDB(){
        return new ContentDBImpl(getDataSource());
    }
}
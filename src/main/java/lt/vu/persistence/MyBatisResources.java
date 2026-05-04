package lt.vu.persistence;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import lt.vu.mybatis.mappers.AutoriusMapper;
import lt.vu.mybatis.mappers.KnygaMapper;
import lt.vu.mybatis.mappers.SkaitytojasMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

@ApplicationScoped
public class MyBatisResources {

    private SqlSessionFactory sqlSessionFactory;

    @PostConstruct
    public void init() {
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/h2database/BibliotekaDB;AUTO_SERVER=TRUE");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");

        Environment environment = new Environment(
                "production",
                new JdbcTransactionFactory(),
                dataSource
        );

        Configuration configuration = new Configuration(environment);

        try {
            // Add mapper interfaces
            configuration.addMapper(AutoriusMapper.class);
            configuration.addMapper(KnygaMapper.class);
            configuration.addMapper(SkaitytojasMapper.class);

            // Load XML mapper files
            String[] mappers = {
                    "lt/vu/biblioteka/mybatis/dao/AutoriusMapper.xml",
                    "lt/vu/biblioteka/mybatis/dao/KnygaMapper.xml",
                    "lt/vu/biblioteka/mybatis/dao/SkaitytojasMapper.xml"
            };

            for (String mapper : mappers) {
                try (java.io.InputStream is =
                             Thread.currentThread()
                                     .getContextClassLoader()
                                     .getResourceAsStream(mapper)) {
                    if (is != null) {
                        org.apache.ibatis.builder.xml.XMLMapperBuilder mapperParser =
                                new org.apache.ibatis.builder.xml.XMLMapperBuilder(
                                        is, configuration, mapper, configuration.getSqlFragments());
                        mapperParser.parse();
                    } else {
                        System.err.println("WARNING: Could not find mapper file: " + mapper);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("MyBatis konfigūracijos klaida", e);
        }

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    @Produces
    @ApplicationScoped
    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
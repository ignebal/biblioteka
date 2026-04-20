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
            // Įkeliame XML mapper failus
            configuration.addMappers("lt.vu.mybatis.mappers");

            // Taip pat įkeliame XML failus tiesiogiai
            org.apache.ibatis.builder.xml.XMLMapperBuilder mapperParser;

            String[] mappers = {
                    "mybatis/AutoriusMapper.xml",
                    "mybatis/KnygaMapper.xml",
                    "mybatis/SkaitytojasMapper.xml"
            };

            for (String mapper : mappers) {
                try (java.io.InputStream is =
                             Thread.currentThread()
                                     .getContextClassLoader()
                                     .getResourceAsStream(mapper)) {
                    if (is != null) {
                        mapperParser = new org.apache.ibatis.builder.xml.XMLMapperBuilder(
                                is, configuration, mapper, configuration.getSqlFragments());
                        mapperParser.parse();
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
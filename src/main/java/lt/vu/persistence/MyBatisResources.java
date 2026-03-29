package lt.vu.persistence;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

@ApplicationScoped
public class MyBatisResources {

    @Produces
    @ApplicationScoped
    public SqlSessionFactory createSqlSessionFactory() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
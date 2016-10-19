package ex.soft.domain.dao.jdbc;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Alex108 on 18.10.2016.
 */

public class AbstractJdbcDaoTest implements TestRule{
    @Override
    public Statement apply(Statement statement, Description description) {

        return null;
    }
}

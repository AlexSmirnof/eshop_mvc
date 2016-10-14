package ex.soft.domain.dao.jdbc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Alex108 on 14.10.2016.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        JdbcPhoneDaoTest.class,
        JdbcOrderDaoTest.class
})
public class SuiteJdbcDao {
}

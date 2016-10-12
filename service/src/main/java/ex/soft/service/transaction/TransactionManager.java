package ex.soft.service.transaction;

import ex.soft.domain.dao.DaoFactory;
import ex.soft.domain.datasource.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Alex108 on 12.10.2016.
 */
public class TransactionManager implements ITransactional {

    private final DataSource dataSource;

    private TransactionManager(){
        dataSource = DaoFactory.getInstance().getDataSource();
    }

    public static ITransactional getInstance(){
        return TransactionManagerHolder.INSTANCE;
    }

    @Override
    public <T> T doInTransaction(IUnitOfWork<T> unitOfWork) {
        Connection connection = dataSource.getConnection();
        try {
            connection.setAutoCommit(false);
            T result = unitOfWork.work();
            connection.commit();
            return result;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                System.out.println(e1.toString());
            }
            System.out.println(e.toString());
        }
        return null;
    }

    private static final class TransactionManagerHolder{
        private static final TransactionManager INSTANCE = new TransactionManager();
        private TransactionManagerHolder(){}
    }
}

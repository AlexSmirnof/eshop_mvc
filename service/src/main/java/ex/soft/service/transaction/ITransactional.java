package ex.soft.service.transaction;

import java.sql.SQLException;

/**
 * Created by Alex108 on 12.10.2016.
 */
public interface ITransactional{

    <T> T doInTransaction(IUnitOfWork<T> unitOfWork);

    public interface IUnitOfWork<T> {

        T work() throws SQLException;
    }
}

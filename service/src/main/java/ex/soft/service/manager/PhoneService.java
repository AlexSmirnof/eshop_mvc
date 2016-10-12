package ex.soft.service.manager;

import ex.soft.domain.dao.DaoFactory;
import ex.soft.domain.dao.PhoneDao;
import ex.soft.domain.model.Phone;
import ex.soft.service.transaction.ITransactional;
import ex.soft.service.transaction.TransactionManager;

import java.util.List;

/**
 * Created by Alex108 on 12.10.2016.
 */
public class PhoneService implements IService<Phone> {

    private PhoneDao phoneDao;
    private ITransactional txManager;

    private PhoneService(){
        phoneDao = DaoFactory.getPhoneDao();
        txManager = TransactionManager.getInstance();
    }

    public static PhoneService getInstance(){
        return PhoneServiceHolder.INSTANCE;
    }

    @Override
    public Phone get(Long key) {
        return txManager.<Phone>doInTransaction(() -> phoneDao.get(key));
    }

    @Override
    public void save(Phone phone) {
        txManager.<Void>doInTransaction(() -> {
            phoneDao.save(phone);
            return null;
        });
    }

    @Override
    public List<Phone> findAll() {
        return txManager.<List<Phone>>doInTransaction(() -> phoneDao.findAll());
    }

    @Override
    public void close() {
        phoneDao.close();
    }

    private static class PhoneServiceHolder{
        private static final PhoneService INSTANCE = new PhoneService();
        private PhoneServiceHolder(){}
    }
}

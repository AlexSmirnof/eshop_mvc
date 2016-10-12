package ex.soft.domain.dao.persistence;

import ex.soft.domain.dao.PhoneDao;
import ex.soft.domain.model.Phone;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;

/**
 * Created by Alex108 on 11.10.2016.
 */
class FilePhoneDao implements PhoneDao {

    private static final String FILE = "phones.xml";

    private FilePhoneDao() {
        init();
    }

    private void init(){
        XMLEncoder encoder = null;
        try{
            new File(FILE).createNewFile();
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(FILE)));
            encoder.setExceptionListener( e -> System.out.println(e.toString()));
            encoder.writeObject(new LinkedHashMap<>());
            encoder.flush();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            if (encoder != null) {
                encoder.close();
            }
        }
    }

    public static FilePhoneDao getInstance(){
        return FilePhoneDaoHolder.INSTANCE;
    }

    private synchronized void writePhoneToFile(Map<Long, Phone> newPhones, String file){
        Map<Long, Phone> phones = readPhoneFromFile(FILE);
        phones.putAll(newPhones);
        XMLEncoder encoder = null;
        try {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
            encoder.setExceptionListener( e -> System.out.println(e.toString()));
            encoder.writeObject(phones);
            encoder.flush();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } finally {
            if (encoder != null) {
                encoder.close();
            }
        }
    }

    private synchronized Map<Long, Phone> readPhoneFromFile(String file){
        XMLDecoder decoder = null;
        Map<Long, Phone> phones = null;
        try{
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
            phones = (Map<Long, Phone>) decoder.readObject();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } finally {
            if (decoder != null) {
                decoder.close();
            }
        }
        return phones;
    }

    @Override
    public Phone get(Long key) {
        return readPhoneFromFile(FILE).get(key);
    }

    @Override
    public void save(Phone phone) {
        writePhoneToFile(Collections.singletonMap(phone.getKey(), phone), FILE );
    }

    @Override
    public List<Phone> findAll() {
        return new ArrayList<>(readPhoneFromFile(FILE).values());
    }

    @Override
    public void close() {
     /*
        NOP
    */
    }

    private static class FilePhoneDaoHolder{
        private static final FilePhoneDao INSTANCE = new FilePhoneDao();

        private FilePhoneDaoHolder() {}
    }

}

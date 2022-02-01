package pl.kuchmaczpogoda.model;

import java.io.*;

public class PersistenceService {

    private String DATA_LOCATION2 = System.getProperty("user.home") + File.separator;

    public String getDataLocation() {
        return DATA_LOCATION2;
    }

    public PersistenceService(String fileName) {
        DATA_LOCATION2 = DATA_LOCATION2 + fileName;
    }

    public void saveToPersistenceMethod(PersistenceData persistenceData) {
        try {
            File file = new File(DATA_LOCATION2);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(persistenceData);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PersistenceData loadFromPersistenceMethod() {
        PersistenceData persistenceData = new PersistenceData();
        try {
            FileInputStream fileInputStream = new FileInputStream(DATA_LOCATION2);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            persistenceData = (PersistenceData) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persistenceData;
    }
}


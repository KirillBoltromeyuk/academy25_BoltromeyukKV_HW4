package by.it_academy.jd2.storage;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.storage.api.exceptions.StorageException;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;

/**
 * Сервис для подключения к СУБД.
 * Осуществляет следующие операции:
 * Хранит параметры для подключения к СУБД;
 * Отправляет эти параметры в другие сервисы работающие с СУБД.
 */
public class StorageFactory {
    private final static DataSource dataSource;

    static {
        try {
            ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setDriverClass( "org.postgresql.Driver" ); //loads the jdbc driver
            cpds.setJdbcUrl( "jdbc:postgresql://localhost:5432/homework4" );
            cpds.setUser("postgres");
            cpds.setPassword("77227722");
            dataSource = cpds;
        } catch (Exception e) {
            throw new StorageException(e.getMessage());
        }
    }

    private final static UserStorage userStorage;
    private final static MessageStorage messageStorage;

    static {
        userStorage = new UserStorage(dataSource);
        messageStorage = new MessageStorage(dataSource);
    }

    private StorageFactory() {}

    public static UserStorage getUserStorage() {
        return userStorage;
    }
    public static MessageStorage getMessageStorage() {
        return messageStorage;
    }
}


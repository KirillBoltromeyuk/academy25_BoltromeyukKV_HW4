package by.it_academy.jd2.storage;

import by.it_academy.jd2.core.ContextFactory;
import by.it_academy.jd2.core.dto.User;
import by.it_academy.jd2.core.dto.UserRole;
import by.it_academy.jd2.storage.api.IUserStorage;
import by.it_academy.jd2.storage.api.exceptions.StorageException;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;

/**
 * Сервис для работы с записи и чтения пользователей из базы данных.
 * Реализует интерфейс {@link IUserStorage}.
 * Осуществляет следующие операции:
 * Запись в СУБД нового пользователя;
 * Проверка есть ли в СУБД пользователь с заданными логином и паролем;
 * Получение пользователя по его логину;
 * Подсчёт общего количества пользователей.
 */
public class UserStorage implements IUserStorage {

    private final DataSource dataSource;

    public UserStorage(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user) {
        String sql = "INSERT into app.users (login, password, name, date_of_birds, date_of_create, role) " +
                "VALUES(?,?,?,?,?,?)";
        try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setTimestamp(4, Timestamp.valueOf(user.getDateOfBirth()));
            pstmt.setTimestamp(5, Timestamp.valueOf(user.getDateOfCreate()));
            pstmt.setString(6, user.getRole().toString());
            pstmt.executeUpdate();
        }catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public User getByLogin(String login) {
        String sql = "SELECT  name, date_of_birds, date_of_create, role FROM app.users WHERE login = ?";
        try(Connection conn= dataSource.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String name = resultSet.getString("name");
            LocalDateTime dateOfBirth = resultSet.getTimestamp("date_of_birds").toLocalDateTime();
            LocalDateTime dateOfCreate = resultSet.getTimestamp("date_of_create").toLocalDateTime();
            UserRole role = UserRole.valueOf(resultSet.getString("role"));

            return new User.Builder()
                    .setLogin(login)
                    .setName(name)
                    .setDateOfBirth(dateOfBirth)
                    .setDateOfCreate(dateOfCreate)
                    .setRole(role)
                    .build();
        }catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public boolean existsByLoginAndPass(String login, String password) {
        String sql = "SELECT * FROM app.users WHERE login = ? AND password = ?";
        try(Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }catch (SQLException e) {
            throw new StorageException(e);
        }

    }

    @Override
    public int getCount() {
        String sql="SELECT COUNT(*) FROM app.users";
        int count=0;
        try(Connection conn= dataSource.getConnection();
        PreparedStatement prst= conn.prepareStatement(sql)){
            prst.execute();
            ResultSet resultSet = prst.getResultSet();
            resultSet.next();
            count = resultSet.getInt(1);
        }catch (SQLException e) {
            throw new StorageException(e);
        }
        return count;
    }
}

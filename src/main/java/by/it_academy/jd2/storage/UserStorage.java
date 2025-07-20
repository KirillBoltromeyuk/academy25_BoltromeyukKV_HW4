package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.User;
import by.it_academy.jd2.dto.UserRole;
import by.it_academy.jd2.storage.api.IUserStorage;
import by.it_academy.jd2.storage.api.exceptions.StorageException;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;


public class UserStorage implements IUserStorage {
    private final DataSource dataSource;

    public UserStorage(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT into app.users (login, password, name, date_of_birds, date_of_create, role) " +
                "VALUES(?,?,?,?,?,?)";
        try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
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
    public User getUserByLogin(String login) {
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
            User.UserBuilder userBuilder = new User.UserBuilder();
            userBuilder.setName(name);
            userBuilder.setDateOfBirth(dateOfBirth);
            userBuilder.setDateOfCreate(dateOfCreate);
            userBuilder.setRole(role);
            return userBuilder.build();
        }catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public boolean UserExists(String login, String password) {
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
}

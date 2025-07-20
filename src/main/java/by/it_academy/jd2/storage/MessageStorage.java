package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.Message;
import by.it_academy.jd2.storage.api.IMessageStorage;
import by.it_academy.jd2.storage.api.exceptions.StorageException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageStorage implements IMessageStorage {

    private final DataSource dataSource;

    public MessageStorage(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void add(Message message) {
        String sql = "INSERT into app.messages (create_date, author, destination, text) " +
                "VALUES(?,?,?,?)";
        try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(message.getTime()));
            stmt.setString(2, message.getAuthor());
            stmt.setString(3, message.getDestination());
            stmt.setString(4, message.getText());
            stmt.executeUpdate();
        }catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public List<Message> getFromDestination(String destination) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT create_date, author, text FROM app.messages WHERE destination = ?";
        try(Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, destination);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                messages.addLast(new Message.Builder()
                                .setTime(rs.getTimestamp("create_date").toLocalDateTime())
                                .setDestination(destination)
                                .setAuthor(rs.getString("author"))
                                .setText(rs.getString("text"))
                        .build());
            }
        }catch (SQLException e) {
            throw new StorageException(e);
        }
        return messages;
    }
}

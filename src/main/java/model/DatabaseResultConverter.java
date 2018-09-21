package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseResultConverter {

    public Message convertMessage(ResultSet rs) throws SQLException {
        return new Message(rs.getInt("id"),rs.getInt("sender_id"),
                rs.getInt("conversation_id"),rs.getString("message"),
                rs.getString("time_sent"));
    }
    public Conversation convertConversation(ResultSet rs) throws SQLException {
        return new Conversation(rs.getInt("id"));
    }
}

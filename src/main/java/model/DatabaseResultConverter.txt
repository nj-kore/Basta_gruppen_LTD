package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseResultConverter {

    public Message convertMessage(ResultSet rs) throws SQLException {
        return new Message(rs.getInt("id"),rs.getInt("sender_id"),
                rs.getInt("conversation_id"),rs.getString("message"),
                rs.getString("time_sent"));
    }
    public Conversation convertConversation(ResultSet details, ArrayList<ResultSet> participants) throws SQLException {
        Conversation c = new Conversation(details.getInt("conversation_id"));

        return c;
    }

    public User convertUser(ResultSet details, ArrayList<ResultSet> contacts) {
        return null;
    }
}

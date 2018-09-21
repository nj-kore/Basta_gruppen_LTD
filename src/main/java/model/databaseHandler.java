package model;

import javafx.fxml.Initializable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class databaseHandler {
    /**
     * Connect to a sample database
     */

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:src/main/java/model/messages.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private void createMessageDb(){
        String url = "jdbc:sqlite:src/main/java/model/messages.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void createNewMessageTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:src/main/java/model/messages.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS messages (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	conversation_id integer NOT NULL,\n"
                + "	sender_id integer NOT NULL,\n"
                + "	message text NOT NULL,\n"
                + "	time_sent text\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Insert a new row into the messages table
     *
     * @param sender_id
     * @param message
     * @param time_sent
     */
    public void insert(int sender_id,int conversation_id, String message, String time_sent) {
        String sql = "INSERT INTO messages(sender_id,conversation_id,message,time_sent) VALUES(?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, sender_id);
            pstmt.setInt(2, conversation_id);
            pstmt.setString(3, message);
            pstmt.setString(4,time_sent);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * select all rows in the messages table
     */
    public List<Message> loadConversationMessages(int conversation){
        String sql = "SELECT id, conversation_id, sender_id, message, time_sent FROM messages WHERE conversation_id=" + conversation;
        ArrayList<Message> messages = new ArrayList<>();
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                messages.add(new Message(rs.getInt("id"),rs.getInt("sender_id"),rs.getInt("conversation_id"),rs.getString("message"),rs.getString("time_sent")));
                System.out.println("Messages has been appended");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        databaseHandler db = new databaseHandler();
        db.createMessageDb();
        createNewMessageTable();
        db.insert(1,1,"hejsan","5");
        db.insert(2,1,"hallå eller","6");
        db.loadConversationMessages(1);

    }
}

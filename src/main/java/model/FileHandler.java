package model;

import java.io.*;
import java.util.Scanner;

public class FileHandler {
    //private String baseDirectory = "../../../resources/conversations/";
    public File read(String fileName) {
        //System.out.println(getClass().getResource(baseDirectory+fileName).getPath());
        //return new File(getClass().getResource(baseDirectory+fileName).getPath());
        return new File(fileName);

    }
    public void write(String fileName, String text) {
        //String path = getClass().getResource(baseDirectory+fileName).getPath();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Conversation loadConversation(int conversationId) {
        File file = read(Integer.toString(conversationId));
        Conversation c = new Conversation(conversationId);

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(scanner.hasNext()) {
            String s = scanner.nextLine();
            String[] parts = s.split(";");
            Message m = new Message(new User(Integer.parseInt(parts[0])), parts[1]);
            c.addMessage(m);
        }
        return c;
    }
}


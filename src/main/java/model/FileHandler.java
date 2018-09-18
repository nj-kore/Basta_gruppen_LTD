package model;

import java.io.*;
import java.util.Scanner;

public class FileHandler {
    private String baseDirectory;

    public FileHandler() {
        baseDirectory = System.getProperty("user.dir")+"\\conversations\\";
        System.out.println(baseDirectory);
    }

    public File read(String fileName) {
        return new File(baseDirectory+fileName);

    }
    public void write(String fileName, String text) throws IOException {
        String path = baseDirectory + fileName;
        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
        writer.write(text);
        writer.close();
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


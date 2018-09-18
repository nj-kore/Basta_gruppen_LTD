package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    private String basePath = "E:\\Chalmers\\Programmering\\TDA367\\Basta_gruppen_LTD\\messages\\";
    public void read(String path, Message message) {

    }
    public void write(String path, Message message) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(basePath+path, true));
        writer.write(message.getSender().getId()+":"+message.getText()+"\n");
        writer.close();
    }
}


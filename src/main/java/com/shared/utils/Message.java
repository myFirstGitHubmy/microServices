package com.shared.utils;

import com.config.Constant;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Message {
    private String fileName;

    public Message(String fileName) {
        this.fileName = fileName;
    }

    public String getMessage() throws FileNotFoundException {
        FileReader fileReader = new FileReader(Constant.ABSOLUTE_PATH + this.fileName + Constant.EXTENSION_TXT);
        Scanner scanner = new Scanner(fileReader);
        StringBuilder st = new StringBuilder();
        while (scanner.hasNextLine()) {
            st.append(scanner.nextLine());
        }
        return st.toString();
    }
}

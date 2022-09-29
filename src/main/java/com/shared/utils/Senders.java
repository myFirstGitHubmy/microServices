package com.shared.utils;

import com.config.Constant;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Senders {
    private String path;

    public Senders(String nameFile) {
        this.path = Constant.ABSOLUTE_PATH + nameFile + Constant.EXTENSION_TXT;
    }

    public List<Sender> getSenders() throws FileNotFoundException {
        FileReader fileReader = new FileReader(this.path);
        Scanner scanner = new Scanner(fileReader);
        List<Sender> senders = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String logPass = scanner.nextLine();
            String[] loginPass = logPass.split(":");
            senders.add(new Sender(loginPass[0], loginPass[1]));
        }
        return senders;
    }
}

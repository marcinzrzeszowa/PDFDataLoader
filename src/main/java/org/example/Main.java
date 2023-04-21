package org.example;

import org.example.structure.ApplicationLoader;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationLoader application = new ApplicationLoader(new TestApplication());
        application.start();
    }
}
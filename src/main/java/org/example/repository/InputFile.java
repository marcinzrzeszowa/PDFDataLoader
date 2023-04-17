package org.example.repository;

abstract class InputFile {
    protected String extension;

    public abstract StringBuilder parseFile(String filePath);

}

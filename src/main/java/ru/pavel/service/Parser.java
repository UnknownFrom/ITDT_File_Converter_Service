package ru.pavel.service;

public final class Parser {
    private Writer writer;
    private Reader reader;

    public Writer getWriter() {
        return writer;
    }

    public Reader getReader() {
        return reader;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}

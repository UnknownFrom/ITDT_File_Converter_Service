package classes;

import interfaces.IReader;
import interfaces.IWriter;

public class ManageExtension {
    private IReader reader;
    private IWriter writer;

    public IWriter getWriter() {
        return writer;
    }

    public void setWriter(IWriter writer) {
        this.writer = writer;
    }

    public IReader getReader() {
        return reader;
    }

    public void setReader(IReader reader) {
        this.reader = reader;
    }
}

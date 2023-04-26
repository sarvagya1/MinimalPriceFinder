package org.example.exception;

public class FileExtensionNotSupportedException extends Exception {
    public FileExtensionNotSupportedException(String fileFormatNotSupported) {
        super(fileFormatNotSupported);
    }
}

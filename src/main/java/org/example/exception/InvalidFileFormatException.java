package org.example.exception;

/**
 * Custom exception which will be thrown if file format is invalid
 */
public class InvalidFileFormatException extends Exception {
    public InvalidFileFormatException(String invalidFileFormat) {
        super(invalidFileFormat);
    }
}

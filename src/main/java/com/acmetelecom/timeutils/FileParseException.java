package com.acmetelecom.timeutils;


public class FileParseException extends Throwable {

	private static final long serialVersionUID = 1L;

	public FileParseException(String s, Exception e) {
        super(s,e);
    }
}

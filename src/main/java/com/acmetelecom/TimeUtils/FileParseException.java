package com.acmetelecom.TimeUtils;

/**
 * Created with IntelliJ IDEA.
 * User: deewar
 * Date: 05/12/12
 * Time: 02:42
 * To change this template use File | Settings | File Templates.
 */
public class FileParseException extends Throwable {
    public FileParseException(String s, Exception e) {
        super(s,e);
    }
}

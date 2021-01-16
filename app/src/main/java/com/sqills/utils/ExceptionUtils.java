package com.sqills.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils
{
    public static String stackTrace( Exception ex )
    {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}

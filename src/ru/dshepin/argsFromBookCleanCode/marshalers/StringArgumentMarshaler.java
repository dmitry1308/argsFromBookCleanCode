package ru.dshepin.argsFromBookCleanCode.marshalers;

import ru.dshepin.argsFromBookCleanCode.exception.ArgsExeption;
import ru.dshepin.argsFromBookCleanCode.exception.ErrorCode;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StringArgumentMarshaler implements ArgumentMarshaler {
	private String stringValue = "";

	public static String getValue(ArgumentMarshaler am) {
		if (am != null && am instanceof StringArgumentMarshaler) {
			return ((StringArgumentMarshaler) am).stringValue;
		} else return "";
	}

	@Override
	public void set(Iterator<String> currentArgument) throws ArgsExeption {
		try {
			stringValue = currentArgument.next();

		}  catch (NoSuchElementException e){
			throw new ArgsExeption(ErrorCode.MISSING_STRING);
		}

	}
}

package ru.dshepin.argsFromBookCleanCode.marshalers;

import ru.dshepin.argsFromBookCleanCode.exception.ArgsExeption;
import ru.dshepin.argsFromBookCleanCode.exception.ErrorCode;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntegerArgumentMarshaler implements ArgumentMarshaler {
	private int intValue = 0;

	public static int getValue(ArgumentMarshaler am) {
		if (am != null && am instanceof IntegerArgumentMarshaler) {
			return ((IntegerArgumentMarshaler) am).intValue;
		} else return 0;
	}

	@Override
	public void set(Iterator<String> currentArgument) throws ArgsExeption {
		String parameter = null;
		try {
			parameter = currentArgument.next();
			intValue = Integer.parseInt(parameter);
		} catch (NoSuchElementException e) {
			throw new ArgsExeption(ErrorCode.MISSING_INTEGER);
		} catch (NumberFormatException e) {
			throw new ArgsExeption(ErrorCode.INVALID_INTEGER, parameter);
		}
	}
}

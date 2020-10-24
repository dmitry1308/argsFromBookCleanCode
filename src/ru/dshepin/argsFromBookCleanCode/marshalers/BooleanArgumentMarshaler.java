package ru.dshepin.argsFromBookCleanCode.marshalers;

import ru.dshepin.argsFromBookCleanCode.exception.ArgsExeption;

import java.util.Iterator;

public class BooleanArgumentMarshaler implements ArgumentMarshaler {
	private boolean booleanValue = false;

	public static boolean getValue(ArgumentMarshaler am) {
		if (am != null && am instanceof BooleanArgumentMarshaler) {
			return ((BooleanArgumentMarshaler) am).booleanValue;
		} else return false;
	}

	@Override
	public void set(Iterator<String> currentArgument) throws ArgsExeption {
		booleanValue = true;
	}
}

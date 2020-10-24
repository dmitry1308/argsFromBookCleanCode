package ru.dshepin.argsFromBookCleanCode.marshalers;

import ru.dshepin.argsFromBookCleanCode.exception.ArgsExeption;

import java.util.Iterator;

public class DoubleArgumentMarshaler implements ArgumentMarshaler {
	public static double getValue(ArgumentMarshaler am) {
		return 0;
	}

	@Override
	public void set(Iterator<String> currentArgument) throws ArgsExeption {

	}
}

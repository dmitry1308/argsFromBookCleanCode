package ru.dshepin.argsFromBookCleanCode.marshalers;

import ru.dshepin.argsFromBookCleanCode.exception.ArgsExeption;

import java.util.Iterator;

public interface ArgumentMarshaler {
	void set(Iterator<String> currentArgument) throws ArgsExeption;

}

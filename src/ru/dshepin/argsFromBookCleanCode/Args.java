package ru.dshepin.argsFromBookCleanCode;

import ru.dshepin.argsFromBookCleanCode.exception.ArgsExeption;
import ru.dshepin.argsFromBookCleanCode.marshalers.*;

import java.util.*;

import static ru.dshepin.argsFromBookCleanCode.exception.ErrorCode.*;

public class Args {
	private Map<Character, ArgumentMarshaler> marshalers;
	private Set<Character> argsFound;
	private ListIterator<String> currentArgument;

	public Args(String schema, String[] args) throws ArgsExeption {
		marshalers = new HashMap<>();
		argsFound = new HashSet<>();
		parseSchema(schema);
		parseArgumentStrings(Arrays.asList(args));
	}

	private void parseSchema(String schema) throws ArgsExeption {
		for (String element : schema.split(",")) {
			if (element.length() > 0) {
				parseSchemaElement(element.trim());
			}
		}
	}

	private void parseSchemaElement(String element) throws ArgsExeption {
		char elementId = element.charAt(0);
		String elementTail = element.substring(1);

		validateSchemaElement(elementId);

		if (elementTail.length() == 0) {
			marshalers.put(elementId, new BooleanArgumentMarshaler());
		}
		else if (elementTail.equals("*")) {
			marshalers.put(elementId, new StringArgumentMarshaler());
		}
		else if (elementTail.equals("#")) {
			marshalers.put(elementId, new IntegerArgumentMarshaler());
		}
		else if (elementTail.equals("##")) {
			marshalers.put(elementId, new DoubleArgumentMarshaler());
		} else {
			throw new ArgsExeption(INVALID_ARGUMENT_FORMAT, elementId, elementTail);
		}
	}

	private void validateSchemaElement(char elementId) throws ArgsExeption {
		if (!Character.isLetter(elementId)) {
			throw new ArgsExeption(INVALID_ARGUMENT_NAME, elementId, null);
		}
	}

	private void parseArgumentStrings(List<String> argsList) throws ArgsExeption {
		for (currentArgument = argsList.listIterator(); currentArgument.hasNext(); ) {
			String argsString = currentArgument.next();
			if (argsString.startsWith("-")) {
				parseArgumentCharacters(argsString.substring(1));
			} else {
				currentArgument.previous();
				break;
			}
		}
	}

	private void parseArgumentCharacters(String argChars) throws ArgsExeption {
		for (int i = 0; i < argChars.length(); i++) {
			parseArgumentCharacter(argChars.charAt(i));
		}
	}

	private void parseArgumentCharacter(char argChar) throws ArgsExeption {
		ArgumentMarshaler m = marshalers.get(argChar);
		if (m == null) {
			throw new ArgsExeption(UNEXPECTED_ARGUMENT, argChar, null);
		} else {
			argsFound.add(argChar);
		}
		try {
			m.set(currentArgument);
		} catch (ArgsExeption e) {
			e.setErrorArgumentId(argChar);
			throw e;
		}
	}

	public boolean has(char arg) {
		return argsFound.contains(arg);
	}

	public int nextArgument() {
		return currentArgument.nextIndex();
	}

	public boolean getBoolean(char arg) {
		return BooleanArgumentMarshaler.getValue(marshalers.get(arg));
	}

	public int getInt(char arg) {
		return IntegerArgumentMarshaler.getValue(marshalers.get(arg));
	}

	public double getDouble(char arg) {
		return DoubleArgumentMarshaler.getValue(marshalers.get(arg));
	}

	public String getString(char arg) {
		return StringArgumentMarshaler.getValue(marshalers.get(arg));
	}
}

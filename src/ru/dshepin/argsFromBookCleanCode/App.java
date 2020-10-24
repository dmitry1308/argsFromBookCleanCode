package ru.dshepin.argsFromBookCleanCode;

import ru.dshepin.argsFromBookCleanCode.exception.ArgsExeption;

public class App {
	public static void main(String[] args) {
		try {
			Args arg = new Args("l ,p#,d*", args);
			boolean logging = arg.getBoolean('l');
			int port = arg.getInt('p');
			String directory = arg.getString('d');
			executeApplication(logging, port, directory);
			executeApplication(arg);


		} catch (ArgsExeption e) {
			System.out.printf("Argument error: %s\n", e.errorMessage());
		}
	}

	private static void executeApplication(Args arg) {
		System.out.println("logging = " + arg.getBoolean('l'));
		System.out.println("port = " + arg.getInt('p'));
		System.out.println("directory = " + arg.getString('d'));
		System.out.println();
	}

	private static void executeApplication(boolean logging, int port, String directory) {
		System.out.println("logging = " + logging);
		System.out.println("port = " + port);
		System.out.println("directory = " + directory);
		System.out.println();
	}
}

package ru.dshepin.argsFromBookCleanCode.exception;

import static ru.dshepin.argsFromBookCleanCode.exception.ErrorCode.OK;

public class ArgsExeption extends Exception {
	private char errorArgumentId = '\0';
	private String errorParameter = null;
	private ErrorCode errorCode = OK;


	public ArgsExeption(String message) {
		super(message);
	}

	public ArgsExeption() {
	}

	public ArgsExeption(ErrorCode errorCode, char elementId, String errorParameter) {
		this.errorCode = errorCode;
		this.errorArgumentId = elementId;
		this.errorParameter = errorParameter;
	}

	public ArgsExeption(ErrorCode code) {
		this.errorCode = code;
	}

	public ArgsExeption(ErrorCode invalidInteger, String parameter) {
		this.errorCode = invalidInteger;
		errorParameter = parameter;
	}

	public String errorMessage() {
		switch (errorCode) {
			case OK:
				return "TILT: Should not get here.";
			case UNEXPECTED_ARGUMENT:
				return String.format("Argument -%C unexpected.", errorArgumentId);
			case MISSING_STRING:
				return String.format("Could not find string parameter for -%C.", errorArgumentId);

			case INVALID_INTEGER:
				return String.format("Argument -%C expects an integer but was '%S'.", errorArgumentId, errorParameter);
			case MISSING_INTEGER:
				return String.format("Could not find integer parameter for -%C.", errorArgumentId);

			case INVALID_DOUBLE:
				return String.format("Argument -%C expects an double but was '%S'.", errorArgumentId, errorParameter);
			case MISSING_DOUBLE:
				return String.format("Could not find double parameter for -%C.", errorArgumentId);

			case INVALID_ARGUMENT_NAME:
				return String.format(" '%C' is not a valid argument name.", errorArgumentId);
			case INVALID_ARGUMENT_FORMAT:
				return String.format(" '%C' is not a valid argument format.", errorArgumentId);

		}

		return "";
	}


	public char getErrorArgumentId() {
		return errorArgumentId;
	}

	public void setErrorArgumentId(char errorArgumentId) {
		this.errorArgumentId = errorArgumentId;
	}

	public String getErrorParameter() {
		return errorParameter;
	}

	public void setErrorParameter(String errorParameter) {
		this.errorParameter = errorParameter;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
}


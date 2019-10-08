package br.com.teste.evoluum.exception;

public class CVSConvertException extends Exception {
	
	
	private static final long serialVersionUID = 5037380682047742174L;

	public CVSConvertException() {
		super("Não foi possível realizar a conversão para o arquivo CVS");
	}
	
	public CVSConvertException(String message) {
		super(message);
	}

}

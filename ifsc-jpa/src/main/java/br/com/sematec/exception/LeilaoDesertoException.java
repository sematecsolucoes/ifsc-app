package br.com.sematec.exception;

public class LeilaoDesertoException extends Exception {
	private static final long serialVersionUID = 1L;

	public LeilaoDesertoException() {
		super("Leilão não possui lances");
	}
}

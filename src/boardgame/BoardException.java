package boardgame;

public class BoardException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public BoardException(String msg) { // Construtor repassa a mensagem para o construtor da superclasse.
		super(msg);
	}

}

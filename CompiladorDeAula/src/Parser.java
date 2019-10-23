import java.io.IOException;

public abstract class Parser {
	/**
	 * Tabela de Símbolos
	 */
	TS ts;
	/**
	 * Analisador Léxico
	 */
	Lexico lexer;
	/**
	 * Token corrente que está sendo analisado
	 */
	Token t;
	
	public Parser(String arquivo) throws IOException {
		ts = new TS();
		lexer = new Lexico(arquivo);
	}

	/**
	 * O método deve ser implementado pelo parser concreto
	 */
	public abstract void parse() throws IOException;
	
	/**
	 * Obtém o próximo token
	 * @throws IOException
	 */
	public void buscaToken() throws IOException {
		t = lexer.buscaToken();
	}
	
	public boolean erro(String erro) {
		System.out.println(erro + ": " + t.linha + ", " + t.coluna);
		return false;
	}
}

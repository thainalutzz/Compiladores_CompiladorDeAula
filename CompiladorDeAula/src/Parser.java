import java.io.IOException;

public abstract class Parser {
	/**
	 * Tabela de S�mbolos
	 */
	TS ts;
	/**
	 * Analisador L�xico
	 */
	Lexico lexer;
	/**
	 * Token corrente que est� sendo analisado
	 */
	Token t;
	
	public Parser(String arquivo) throws IOException {
		ts = new TS();
		lexer = new Lexico(arquivo);
	}

	/**
	 * O m�todo deve ser implementado pelo parser concreto
	 */
	public abstract void parse() throws IOException;
	
	/**
	 * Obt�m o pr�ximo token
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

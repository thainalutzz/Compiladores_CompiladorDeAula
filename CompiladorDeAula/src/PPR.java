import java.io.IOException;

public class PPR extends Parser {
	public PPR(String arquivo) throws IOException {
		super(arquivo);
	}
	
	@Override
	public void parse() throws IOException {
		// TODO Auto-generated method stub
		analisaPrograma();	
	}	
	
	public boolean analisaPrograma() throws IOException {
		buscaToken();
		if(t.tipo == Tipo.SPROGRAMA) {
			System.out.println(t.tipo + " ");
			buscaToken();
			if (t.tipo == Tipo.SIDENTIFICADOR) {
				System.out.println(t.tipo +": " +t.lexema +' ');
				//Adiciona identficados a tabela de simbolos
				ts.ts.put(t.lexema, t);
				buscaToken();
				if(t.tipo == Tipo.SPONTO_E_VIRGULA) {
					System.out.println(t.tipo + " ");
					if(analisaBloco()) {
						System.out.println("SBLOCO ");
						buscaToken();
						if(t.tipo == Tipo.SPONTO) {
							System.out.println(t.tipo + " ");
							return true;
						} else {
							return erro("Ponto final nao encontrado");
						}
					} else {
						return erro("Bloco principal nao encontrado");
					}
				} else {
					erro("Ponto e virgula esperado");
					return false;
				} 
			} else {
				return erro("Identificador esperado");
			}
		} else {
			return erro("Nao foi encontrado o programa principal.");
		}
	}
	
	public boolean analisaBloco() throws IOException {
		return true;
		//buscaToken();
		//return analisaEtapaDeclaracaoDeVariaveis();
	}
	
	public boolean analisaEtapaDeclaracaoDeVariaveis() throws IOException {
		if(t.tipo == Tipo.SVAR) {
			System.out.println(t.tipo + " ");
			buscaToken();
			if (t.tipo == Tipo.SIDENTIFICADOR) {
				System.out.println(t.tipo +": " +t.lexema +' ');
				while(t.tipo == Tipo.SIDENTIFICADOR) {
					//analisaVariaveis();
					if(t.tipo == Tipo.SPONTO_E_VIRGULA) {
						System.out.println(t.tipo + " ");
						buscaToken();
						return true;
					} else {
						return erro("Ponto e virgula esperado");
					}
				}
			} else {
				return erro("Identificador esperado");
			}
		} else {
			return erro("Var esperado");
		}
		return true;
	}	
}

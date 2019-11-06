import java.io.IOException;

public class PPR extends Parser {
	public PPR(String arquivo) throws IOException {
		super(arquivo);
	}
	
	@Override
	public void parse() throws IOException {
		analisaPrograma();	
	}	
	
	public boolean analisaPrograma() throws IOException {
		buscaToken();
		if(t.tipo == Tipo.SPROGRAMA) {
			System.out.println(t.tipo);
			buscaToken();
			if (t.tipo == Tipo.SIDENTIFICADOR) {
				System.out.println(t.tipo +": " + t.lexema);
				//Adiciona identficados a tabela de simbolos
				ts.ts.put(t.lexema, t);
				buscaToken();
				if(t.tipo == Tipo.SPONTO_E_VIRGULA) {
					System.out.println(t.tipo);
					if(analisaBloco()) {
						buscaToken();
						if(t.tipo == Tipo.SPONTO) {
							System.out.println(t.tipo);
							return true;
						} else {
							return erro("Ponto final esperado");
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
		buscaToken();
		if(t.tipo == Tipo.SINICIO) {
			System.out.println(t.tipo +": " +t.lexema +' ');
			buscaToken();
		}
		analisaEtapaDeclaracaoDeVariaveis();
			//buscaToken();

		System.out.println(t.tipo);
		if(t.tipo == Tipo.SFIM) {
			System.out.println(t.tipo);
			buscaToken();			
		}
		return true;
	}
	
	public boolean analisaEtapaDeclaracaoDeVariaveis() throws IOException {
		if(t.tipo == Tipo.SVAR) {
			System.out.println(t.tipo + " ");
			buscaToken();
			if (t.tipo == Tipo.SIDENTIFICADOR) {				
				while(t.tipo == Tipo.SIDENTIFICADOR) {
					if(analisaVariaveis()) {
						buscaToken();
						if(t.tipo == Tipo.SPONTO_E_VIRGULA) {
							System.out.println(t.tipo + " ");
							buscaToken();
							return true;
						} else {
							return erro("Ponto e virgula esperado");
						}
					} else {
						return erro("Erro na declaracao de variaveis");
					}
				}
			} else {
				return erro("Identificador esperado");
			}
		}
		return true;
	}
	public boolean analisaVariaveis() throws IOException {
		do {
			if(t.tipo == Tipo.SIDENTIFICADOR) {
				verificaDuplicidade();
				System.out.println(t.tipo +": " +t.lexema +' ');
				ts.ts.put(t.lexema, t);				
				buscaToken();
				if(t.tipo == Tipo.SVIRGULA || t.tipo == Tipo.STIPO) {
					System.out.println(t.tipo + " ");
					if(t.tipo == Tipo.SVIRGULA) {
						System.out.println(t.tipo + " ");
						buscaToken();
						if(t.tipo == Tipo.STIPO) {
							return erro("Token incorreto encontrado.");
						}
					}						
				} else {
					return erro("Declaracao de variavel incorreta");
				}
				//fecha if da duplicidade
				//else
				//retorna erro de duplicidade encontrada
			} else {
				return erro("Identificador esperado");
			}
		} while (t.tipo != Tipo.STIPO);
		buscaToken();
		return analisaTipo();
		//return true;
	}
	
	public boolean analisaTipo() throws IOException {
		if(t.tipo != Tipo.SINTEIRO && t.tipo != Tipo.SINTEIRO) {
			return erro("Tipo não reconhecido");
		}
		else {
			System.out.println(t.tipo + " ");
			//Adiciona identficados a tabela de simbolos
			ts.ts.put(t.lexema, t);
		} 
		return true;
	}
	
	public boolean verificaDuplicidade() throws IOException {
		if(ts.ts.containsKey(t.lexema))
		{
			return erro("Identificador já declarado no escopo.");
		}
		return true;
	}
}

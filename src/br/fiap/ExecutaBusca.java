package br.fiap;

public class ExecutaBusca {

	public static void main(String[] args) {
		
		Busca busca = new Busca(); 
		busca.setEstadoInicial(new Estado(true, true, false));
		busca.resolver();
		
		if(busca.getSolucao() == null) {
			System.out.println("Nao ha solucao para o problema");
		} else {
			System.out.println("A solucao: ");
			for(Acao a : busca.getSolucao()) {
				System.out.println(a);
			}
		}

	}

}

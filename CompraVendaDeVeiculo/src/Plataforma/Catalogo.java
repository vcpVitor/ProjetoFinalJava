package Plataforma;

public class Catalogo {
	private Veiculo[] veiculos;
	private int capacidadeArray;
	private int count;
	
	public Catalogo(int qtdElem) {
		this.veiculos = new Veiculo[qtdElem];
		this.capacidadeArray = qtdElem;
		this.count = 0;
	}
	
	public static Catalogo filtrarPorPreco(Catalogo cat, int min, int max) {
		Catalogo catfilter = new Catalogo(cat.count);
		Veiculo[] veiculos = cat.getListaVeiculos();
		for (int i = 0; i < cat.count; i++) {
			if (veiculos[i].getPreco() >= min && veiculos[i].getPreco() <= max) {
				catfilter.addVeiculo(veiculos[i]);
			}
		}
		return catfilter;
	}
	/**
	 * filtra os carros dentro de um intervalo de preço
	 * @param cat
	 * @param filtro
	 * @return
	 */
	
	public static Catalogo filtrarPorMarca(Catalogo cat, String filtro) {
		Catalogo catfilter = new Catalogo(cat.count);
		Veiculo[]veiculos = cat.getListaVeiculos();
		for (int i = 0; i< cat.count; i++) {
			if (veiculos[i].getMarca().toLowerCase().contains(filtro.toLowerCase())) {
				catfilter.addVeiculo(veiculos[i]);
			}
		}
		return catfilter;
	}
	/**
	 * filtra os carros por marca
	 * @param v
	 */
	
	public void addVeiculo(Veiculo v) {
		this.veiculos[count] = v;
		this.count++;
	}
	
	public void print() {
		for (int i = 0; i < count; i++) { 
			System.out.println(veiculos[i]);
		}
	}
	
	public int length() {
		return count;
	}
	
	public Veiculo[] getListaVeiculos() {
		return veiculos;
	}
}
/**
 *  adiciona veiculos ao catalogo
 */

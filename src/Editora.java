/* Criando a Classe Editora, com a criação de seus construtores, GETs, 
 * SETs e Métodos devidos.
 *
 * 
 * 
 * FATEC Carapicuíba - ADS Manhã
 * Programação Orientada a Objetos
 * Profª Sandra Geroldo
 * 
 * Matheus Andrade Farkas
 * Diego Bezerra da Silva
 */
public class Editora {
	// variaveis privadas.
	private static int codEdit = -100;
	private int codEditora;
	private String razaoSocial;
	private String cnpj;
	private String telefone;

	// Construtor sem parametros com somador de codigo automatico.
	public Editora() {
		this.codEditora = codEdit;
		this.razaoSocial = "";
		this.cnpj = "";
		this.telefone = "";
		codEdit++;
	}

	// Construtor com parametros com somador de codigo automatico.
	public Editora(String razaoSocial, String cnpj, String telefone) {
		this.codEditora = codEdit;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.telefone = telefone;
		codEdit++;
	}

	// Construtor com parametros.
	public Editora(int codEditora, String razaoSocial, String cnpj, String telefone) {
		this.codEditora = codEditora;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.telefone = telefone;
	}

	// Construtor por copia.
	public Editora(Editora e) {
		this.codEditora = e.codEditora;
		this.razaoSocial = e.razaoSocial;
		this.cnpj = e.cnpj;
		this.telefone = e.telefone;
	}

	/*
	 * Gets Sua criacao permite que os valores dos atributos do objeto sejam lidos,
	 * impressos por outras classes e metodos.
	 */
	/*
	 * Sets Sua criacao permite que os valores dos atributos do objeto sejam
	 * alterados quanto chamados.
	 */
	public int getCodEditora() {
		return codEditora;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	// Metodo para validar telefone.
	public static boolean validarTelefone(String telefone) {
		if (telefone.length() == 10) {
			return true;
		} else {
			System.out.println("Numero de telefone invalido!!");
			return false;
		}
	}

	// Metodo para validar CNPJ.
	public static boolean validarCNPJ(String cnpj) {
		if (cnpj.length() == 14) {
			return true;
		} else {
			System.out.println("CNPJ invalido!!");
			return false;
		}
	}
}

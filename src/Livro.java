/* Criando a Classe Livro, com a criação de seus construtores, GETs, 
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
 */public class Livro {
	// variaveis privadas.
	private static int codLiv = 1;
	private int codLivro;
	private String titulo;
	private String autor;
	private String isbn;
	// criacao da variavel Editora editora para poder fazer o relacionamento entre
	// as classes Editora Livro, (atributo de um objeto).
	private int codEditora;

	// Construtor sem parametros.
	public Livro() {
		this.codLivro = 0; // -4002;
		this.titulo = "";
		this.autor = "";
		this.isbn = "";
		this.codEditora = 0;
	}

	// Construtor com parametros com somador de codigo automatico.
	public Livro(String titulo, String autor, String isbn, int i) {
		this.codLivro = codLiv;
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.codEditora = i;
		codLiv++;
	}

	// Construtor com parametros.
	public Livro(int codLivro, String titulo, String autor, String isbn, int i) {
		this.codLivro = codLivro;
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.codEditora = i;
	}

	// Construtor por copia.
	public Livro(Livro l) {
		this.codLivro = l.codLivro;
		this.titulo = l.titulo;
		this.autor = l.autor;
		this.isbn = l.isbn;
		this.codEditora = l.codEditora;
	}

	/*
	 * Gets Sua criacao permite que os valores dos atributos do objeto sejam lidos,
	 * impressos por outras classes e metodos.
	 */
	/*
	 * Sets Sua criacao permite que os valores dos atributos do objeto sejam
	 * alterados quanto chamados.
	 */
	public int getCodLivro() {
		return this.codLivro;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public String getAutor() {
		return this.autor;
	}

	public String getISBN() {
		return this.isbn;
	}

	public int getEditora() {
		return this.codEditora;
	}

	public void setTitulo(String t) {
		this.titulo = t;
	}

	public void setAutor(String a) {
		this.autor = a;
	}

	public void setISBN(String i) {
		this.isbn = i;
	}

	public void setEditora(int e) {
		this.codEditora = e;
	}

	// Metodo para validar o nome do Autor.
	public static boolean validarAutor(String autor) {
		boolean flag = true;
		int i;
		int cont = 0;
		int comprimento;
		int[] x;
		comprimento = autor.length();
		x = new int[comprimento];

		/*
		 * Analisa a string em cada caracteres e tenta encontrar a ocorrencia de ' '
		 * (espaços) na string.
		 */
		for (i = 0; i < comprimento; i++) {
			if (autor.charAt(i) == ' ') {
				x[cont] = i;
				cont++;
			}
		}
		/*
		 * Se nao houver nenhum espaco, significa que nao tem duas palavras na string,
		 * logo ela esta invalida
		 */
		if (cont == 0) {
			flag = false;
		}
		/*
		 * Se houver um espaco, ele vai analisar o inicio e o final da string quantos
		 * caracteres tem a palavra digitada
		 */
		else if (cont == 1) {
			if (x[0] < 2 || (comprimento - 1 - x[0]) < 2) {
				flag = false;
			}
		} else if (cont > 1) {
			if (x[0] < 2 || (comprimento - 1 - x[cont - 1]) < 2) {
				flag = false;
			}
			for (i = 0; i < cont - 1; i++) {
				if ((x[i + 1] - x[i]) < 3) {
					flag = false;
				}
			}
		}
		if (!flag) {
			System.out.println("Autor invalido!!");
		}
		return flag;
	}

	// Metodo para validar o ISBN do livro.
	public static boolean validarISBN(String isbn) {
		// ISBN estiver vazio.
		if (isbn == null) {
			System.out.println("ISBN invalido!!");
			return false;
		}

		// tirar todos os caracteres que nao sejam numeros.
		isbn = isbn.replaceAll("-", "");

		// O ISBN deve ter 13 caracteres.
		if (isbn.length() != 13) {
			System.out.println("ISBN invalido!!");
			return false;
		}

		/*
		 * 1-3 digitos: Tipo 4-5 digtos: Pais 6-12 digitos : Editora, Tipos 13 digito
		 * verificador
		 */
		try {
			// ler todos os digitos.
			int tot = 0;
			for (int i = 0; i < 12; i++) {
				// Fazer a soma do primeiro pelo segundo vezes 3.
				// EX: x1+3x2+x3+3x4+x5+3x6.....
				int digit = Integer.parseInt(isbn.substring(i, i + 1));
				// pegar a soma desses valores.
				tot += (i % 2 == 0) ? digit * 1 : digit * 3;
			}
			// Pegar o primeiro numero multiplo de 10 maior que o valor somado em "tot".
			int checksum = 10 - (tot % 10);
			if (checksum == 10) {
				checksum = 0;
			}
			// Retornando o digito verificador.
			return checksum == Integer.parseInt(isbn.substring(12));
		} catch (NumberFormatException nfe) {
			// Captura ISBNs invalidos que contenham caracteres não numericos.
			System.out.println("ISBN invalido!!");
			return false;
		}
	}
}

/* Criando a Classe Main, com a criação de um menu para Livraria
 * com o seus devidos métodos de CRUD.
 * 
 * 
 * FATEC Carapicuíba - ADS Manhã
 * Programação Orientada a Objetos
 * Profª Sandra Geroldo
 * 
 * Matheus Andrade Farkas
 * Diego Bezerra da Silva
 */
import java.util.Scanner;

public class Main {

	private static Scanner entra = new Scanner(System.in);
	private static boolean flag = false;

	public static void main(String[] args) {
		Livraria Liv = new Livraria();
		Liv.conectar();
		if (Liv.estaConectado()) {
			int op = 1;
			while (op != 0) {
				System.out.print("\nConectado");
				System.out.println("\n|=======================Livraria=====================|");
				System.out.println(
						"Escolha o tipo de cadastro que deseja efetuar:\n--> 1-Cadastrar Editora\n--> 2-Cadastrar Livro\n\n-->"
								+ " 3-Listar editoras\n--> 4-Listar livros\n\n--> 5-Excluir livro\n--> 6-Excluir Editora\n\n--> 7-Editar Editora\n-->"
								+ " 8-Editar Livro\n\n--> 0-Sair");
				op = entra.nextInt();
				entra.nextLine();
				switch (op) {
				case 0:
					System.out.print("Processo Finalizado!!!");
					System.out.println("\nBanco de dados esta desconectado!!!");
					Liv.desconectar();

					break;
				case 1:
					cadastrarEditora();
					System.out.println("\nDigite qualquer tecla para continuar...");
					entra.nextLine();
					break;
				case 2:
					cadastrarLivro();
					System.out.println("\nDigite qualquer tecla para continuar...");
					entra.nextLine();
					break;
				case 3:
					Liv.listarEditoras();
					System.out.println("\nDigite qualquer tecla para continuar...");
					entra.nextLine();
					break;
				case 4:
					Liv.listarLivros();
					System.out.println("\nDigite qualquer tecla para continuar...");
					entra.nextLine();
					break;
				case 5:
					excluirLivros();
					System.out.println("\nDigite qualquer tecla para continuar...");
					entra.nextLine();
					break;
				case 6:
					excluirEditoras();
					System.out.println("\nDigite qualquer tecla para continuar...");
					entra.nextLine();
					break;
				case 7:
					editarEditoras();
					System.out.println("\nDigite qualquer tecla para continuar...");
					entra.nextLine();
					break;
				case 8:
					editarLivros();
					System.out.println("\nDigite qualquer tecla para continuar...");
					entra.nextLine();
					break;
				default:
					System.out.println("Digito invalido!!\n");
					op = 1;
					break;
				}
			}
		} else {
			System.out.println("Não foi possível conectar");
		}
	}

	public static void editarEditoras() {
		Livraria Liv = new Livraria();
		Liv.estaConectado();
		Liv.conectar();
		String razaoSocial;
		String cnpj;
		String telefone;
		if (Liv.listarEditoras() == true) {
			System.out.println("|========================Editar======================|");
			do {
				System.out.print("\nDigite o codigo da editora que deseja alterar ou '0' para sair: ");
				int j = entra.nextInt();
				entra.nextLine();
				if (j == 0) {
					flag = true;
				} else {
					Editora edit = Liv.consultarEditoras(j);
					if (edit.getCodEditora() == j) {
						System.out.println("|====================Editora=====================|");
						System.out.print("Digite a razao social da Editora: ");
						razaoSocial = entra.nextLine();
						boolean a = false;
						do {
							System.out.print("Digite o CNPJ da Editora (14 digitos, sem pontuacao): ");
							cnpj = entra.nextLine();
							a = Editora.validarCNPJ(cnpj);
						} while (!a);
						boolean b = false;
						do {
							System.out.print("Digite o telefone da Editora (DDD 'XX', sem pontuacao e espaco): ");
							telefone = entra.nextLine();
							b = Editora.validarTelefone(telefone);
						} while (!b);
						Liv.editarEditora(j, razaoSocial, cnpj, telefone);
						System.out.println("\nEditora atualizada com sucesso!!!");
						flag = true;
					} else {
						System.out.println("\nEssa editora nao existe no cadastro!!");
						flag = false;
					}
				}
			} while (!flag);
		}
	}

	public static void editarLivros() {
		Livraria Liv = new Livraria();
		Liv.estaConectado();
		Liv.conectar();
		String titulo;
		String autor;
		String isbn;
		if (Liv.listarLivros() == true) {
			System.out.println("|========================Editar======================|");
			do {
				System.out.print("\nDigite o codigo do livro que deseja alterar ou '0' para sair: ");
				int j = entra.nextInt();
				if (j == 0) {
					flag = true;
				} else {
					Livro livro = Liv.consultarLivros(j);
					if (livro.getCodLivro() == j) {
						System.out.println("|========================Livro=======================|");
						entra.nextLine();
						System.out.print("Digite o Titulo do livro: ");
						titulo = entra.nextLine();

						boolean e = false;
						do {
							System.out.print("Digite o nome do autor(a) do livro: ");
							autor = entra.nextLine();
							e = Livro.validarAutor(autor);
						} while (!e);

						boolean f = false;
						do {
							System.out.print("Digite o ISBN do livro (ISBN valida:9788542217759): ");
							isbn = entra.nextLine();
							f = Livro.validarISBN(isbn);
						} while (!f);

						do {
							Liv.listarEditoras();
							System.out.print("\nDigite o codigo da editora que deseja: ");
							int i = entra.nextInt();
							Editora edit = Liv.consultarEditoras(i);
							if (edit.getCodEditora() == i) {
								Liv.editarLivros(j, titulo, autor, isbn, i);
								System.out.println("\nLivro atualizado com sucesso!!!");
								flag = true;
							} else {
								System.out.println("\nEssa editora nao existe no cadastro!!");
								flag = false;
							}
						} while (!flag);
					} else {
						System.out.println("\nEsse livro nao existe no cadastro!!");
						flag = false;
					}
				}
			} while (!flag);
		}
	}

	public static void excluirLivros() {
		Livraria Liv = new Livraria();
		Liv.estaConectado();
		Liv.conectar();
		if (Liv.listarLivros() == true) {
			System.out.println("|======================Excluir======================|");
			do {
				System.out.print("\nDigite o codigo do livro que deseja excluir ou '0' para sair: ");
				int j = entra.nextInt();
				if (j == 0) {
					flag = true;
				} else {
					Livro livro = Liv.consultarLivros(j);
					if (livro.getCodLivro() == j) {
						Liv.apagarLivro(j);

						flag = true;

						System.out.println("\nLivro excluido com sucesso!!!");
						Liv.desconectar();
					} else {
						System.out.println("\nEste livro nao existe no cadastro!!");
						flag = false;
					}
				}
			} while (!flag);
		}
	}

	public static void excluirEditoras() {
		Livraria Liv = new Livraria();
		Liv.estaConectado();
		Liv.conectar();
		if (Liv.listarEditoras() == true) {
			System.out.println("|======================Excluir======================|");
			do {
				System.out.print("\nDigite o codigo da editora que deseja excluir ou '0' para sair: ");
				int j = entra.nextInt();
				if (j == 0) {
					flag = true;
				} else {
					Editora edit = Liv.consultarEditoras(j);
					if (edit.getCodEditora() == j) {
						Liv.apagarEditora(j);
						flag = true;
						System.out.println("\nEditora excluida com sucesso!!!");
						Liv.desconectar();
					} else {
						System.out.println("\nEssa editora nao existe no cadastro!!");
						flag = false;
					}
				}
			} while (!flag);
		}
	}

	public static void cadastrarEditora() {
		Livraria Liv = new Livraria();
		Liv.estaConectado();
		Liv.conectar();
		String razaoSocial;
		String cnpj;
		String telefone;
		System.out.println("|====================Cadastrar=====================|");
		do {
			System.out.print("\nDigite 1 para cadastrar a editora ou '0' para voltar: ");
			int j = entra.nextInt();
			if (j == 0) {
				flag = true;
			} else {
				System.out.println("|=====================Editora======================|");
				entra.nextLine();
				System.out.print("Digite a razao social da Editora: ");
				razaoSocial = entra.nextLine();

				boolean a = false;
				do {
					System.out.print("Digite o CNPJ da Editora (14 digitos, sem pontuacao): ");
					cnpj = entra.nextLine();
					// metodo de validacao.
					a = Editora.validarCNPJ(cnpj);
				} while (!a);

				boolean b = false;
				do {
					System.out.print("Digite o telefone da Editora (DDD 'XX', sem pontuacao e espaco): ");
					telefone = entra.nextLine();
					b = Editora.validarTelefone(telefone);
				} while (!b);

				Liv.inserirEditora(razaoSocial, cnpj, telefone);
				flag = true;
				System.out.println("\nEditora cadastrada com sucesso!!!!");
				Liv.desconectar();
			}
		} while (!flag);
	}

	public static void cadastrarLivro() {
		Livraria Liv = new Livraria();
		Liv.estaConectado();
		Liv.conectar();
		String titulo;
		String autor;
		String isbn;
		if (Liv.listarEditoras() == true) {
			System.out.println("|======================Cadastrar=====================|");
			do {
				System.out.print("\nDigite 1 para cadastrar o livro ou '0' para voltar: ");
				int j = entra.nextInt();
				if (j == 0) {
					flag = true;
				} else {
					System.out.println("|========================Livro=======================|");
					entra.nextLine();
					System.out.print("Digite o Titulo do livro: ");
					titulo = entra.nextLine();

					boolean e = false;
					do {
						System.out.print("Digite o nome do autor(a) do livro: ");
						autor = entra.nextLine();
						e = Livro.validarAutor(autor);
					} while (!e);

					boolean f = false;
					do {
						System.out.print("Digite o ISBN do livro (ISBN valida:9788542217759): ");
						isbn = entra.nextLine();
						f = Livro.validarISBN(isbn);
					} while (!f);
					do {
						System.out.println("Escolha uma editora: ");
						Liv.listarEditoras();
						System.out.print("\nDigite o codigo da editora que deseja: ");
						int i = entra.nextInt();
						Editora edit = Liv.consultarEditoras(i);
						entra.nextLine();
						if (edit.getCodEditora() == i) {
							Liv.inserirLivro(titulo, autor, isbn, edit.getCodEditora());
							flag = true;
							System.out.println("\nLivro cadastrado com sucesso!!!");
							Liv.desconectar();
						} else {
							System.out.println("\nEssa editora nao existe no cadastro!!!");
							flag = false;
						}
					} while (!flag);
				}
			} while (!flag);
		}
	}
}

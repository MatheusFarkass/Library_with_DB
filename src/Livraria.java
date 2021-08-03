/* Criando a Classe que faz a conexão com o MYSQL, com as suas devidas conexões 
 * de CRUD com o programa em JAVA para o SGBD MYSQL workbench.
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Livraria {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultset = null;

//Conexão com o SGBDs
	public void conectar() {
		String servidor = "jdbc:mysql://localhost:3306/livraria?useTimezone=true";
		String timezone = "&serverTimezone=UTC&useSSL=false";
		String string = servidor+timezone;
		String usuario = "root";
		String senha = "";
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
			this.connection = DriverManager.getConnection(string, usuario, senha);
			this.statement = this.connection.createStatement();
		} catch (Exception e) {
			System.out.println("Erro" + e.getMessage());
		}
	}

	public boolean estaConectado() {
		if (this.connection != null) {
			System.out.println("Banco de dados esta conectado!!!");
			return true;
		} else {
			return false;
		}
	}

	public boolean listarLivros() {
		boolean op = true;
		try {
			String query = "select * from livro order by codLivro";
			this.resultset = this.statement.executeQuery(query);
			while (this.resultset.next()) {
				System.out.println("|====================Livro" + (this.resultset.getString("codLivro"))
						+ "======================|");
				System.out.println("Cod: " + this.resultset.getString("codLivro") + " \nTitulo: "
						+ this.resultset.getString("titulo") + " \nAutor: " + this.resultset.getString("autor")
						+ " \nISBN: " + this.resultset.getString("isbn") + " \nEditora: "
						+ this.resultset.getString("codEditora"));
			}
			if (!resultset.last()) {
				System.out.println("|========================Livro=======================|");
				System.out.println("Nao ha nenhum livro cadastrado!!!");
				op = false;
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return op;
	}

	public boolean listarEditoras() {
		boolean ope = true;
		try {
			String query = "select * from editora order by codEditora";
			this.resultset = this.statement.executeQuery(query);
			while (this.resultset.next()) {
				System.out.println("\n|======================Editora" + (this.resultset.getString("codEditora"))
						+ "====================|");
				System.out.println("Cod: " + this.resultset.getString("codEditora") + " \nRazão Social: "
						+ this.resultset.getString("razaoSocial") + " \nCNPJ: " + this.resultset.getString("CNPJ")
						+ " \ntelefone: " + this.resultset.getString("telefone"));
			}
			if (!resultset.last()) {
				System.out.println("|=======================Editora======================|");
				System.out.println("Nao ha nenhuma editora cadastrada!!!");
				ope = false;
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return ope;
	}

	public Editora consultarEditoras(int codEditora) {
		try {
			String query = "SELECT * FROM editora WHERE codEditora = " + codEditora + ";";
			this.resultset = this.statement.executeQuery(query);
			if (resultset.next()) {
				Editora edit = new Editora(resultset.getInt("codEditora"), resultset.getString("razaoSocial"),
						resultset.getString("CNPJ"), resultset.getString("telefone"));
				return edit;
			} else {
				Editora edit = new Editora();
				return edit;
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return null;
	}

	public Livro consultarLivros(int codLivro) {
		try {
			String query = "SELECT * FROM livro WHERE codLivro = " + codLivro + ";";
			this.resultset = this.statement.executeQuery(query);
			if (resultset.next()) {
				Livro livro = new Livro(resultset.getInt("codLivro"), resultset.getString("titulo"),
						resultset.getString("autor"), resultset.getString("isbn"), resultset.getInt("codEditora"));
				return livro;
			} else {
				Livro livro = new Livro();
				return livro;
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return null;
	}

	public void inserirLivro(String titulo, String autor, String isbn, int codEdi) {
		try {
			String query = "insert into livro (titulo,autor,isbn,codEditora) values ('" + titulo + "', '" + autor
					+ "', '" + isbn + "', '" + codEdi + "');";
			this.statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public void inserirEditora(String razao, String cnpj, String tel) {
		try {
			String query = "insert into editora (razaoSocial,CNPJ,telefone) values ('" + razao + "', '" + cnpj + "', '"
					+ tel + "');";
			this.statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public void editarEditora(int cod, String razao, String cnpj, String tel) {
		try {
			String query = "update editora set razaoSocial = '" + razao + "', CNPJ = '" + cnpj + "', telefone = '" + tel
					+ "' where codEditora = " + cod + ";";
			this.statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public void editarLivros(int cod, String titulo, String autor, String isbn, int codEdi) {
		try {
			String query = "update livro set titulo = '" + titulo + "', autor = '" + autor + "', isbn = '" + isbn
					+ "', codEditora = '" + codEdi + "' where codLivro = " + cod + ";";
			this.statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public void apagarLivro(int cod) {
		try {
			String query = "delete from livro where codLivro = " + cod + ";";
			this.statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public void apagarEditora(int cod) {
		try {
			String query = "delete from editora where codEditora = " + cod + ";";
			this.statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public void desconectar() {
		try {
			this.connection.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

}

// Define o pacote onde a classe está localizada
package login;

// Importa as classes necessárias para conectar ao banco de dados MySQL
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// Declara a classe pública User
public class User {

    // Método responsável por conectar ao banco de dados
    public Connection conectarBD(){
        Connection conn = null; // Cria uma variável de conexão nula para iniciar
        try{
            // Possível erro aqui:
            // Se o driver do MySQL (mysql-connector-j.jar) não estiver no classpath,
            // esta linha lança ClassNotFoundException, impedindo a conexão.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Define a URL de conexão (endereço do banco, usuário e senha)
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";

            // Estabelece a conexão com o banco
            conn = DriverManager.getConnection(url);

        }catch (ClassNotFoundException e) {
            // Driver JDBC não encontrado
            System.out.println("Erro: Driver do MySQL não encontrado. Adicione o mysql-connector-j.jar ao classpath.");
            e.printStackTrace();

        }catch (Exception e) {
            // Outros erros de conexão (URL, banco, usuário ou senha incorretos, por exemplo)
            System.out.println("Erro ao conectar com o banco de dados. Verifique a URL, usuário e senha.");
            e.printStackTrace();
        }

        // Retorna a conexão (pode ser nula se houver falha)
        return conn;
    }

    // Declaração de variáveis da classe
    public String nome = "";       // Armazena o nome do usuário encontrado no banco
    public boolean result = false; // Indica se o login foi bem-sucedido

    // Método para verificar se o usuário existe no banco
    public boolean verificarUsuario(String login, String senha){
        String sql = ""; // String que vai conter o comando SQL
        Connection conn = conectarBD(); // Abre conexão com o banco

        // Possível origem de NullPointerException:
        // se conectarBD() falhar e retornar null, qualquer uso de conn
        // (como conn.createStatement()) causaria erro. Por isso é feita a verificação.
        if (conn == null) {
            System.out.println("Conexão falhou. O programa não conseguiu se comunicar com o banco de dados.");
            return false;
        }

        // Monta o comando SQL de consulta
        sql += "select nome from usuarios ";
        sql += "where login = '" + login + "'";
        sql += " and senha = '" + senha + "'";

        try{
            // Cria um objeto Statement para executar o SQL
            Statement st = conn.createStatement();

            // Executa a consulta e armazena o resultado no ResultSet
            ResultSet rs = st.executeQuery(sql);

            // Se existir pelo menos um registro com o login e senha informados
            if(rs.next()){
                result = true; // Define que o login foi bem-sucedido
                nome = rs.getString("nome"); // Pega o nome do usuário do banco
            }

        }catch (Exception e) {
            // Caso ocorra erro na execução da consulta
            e.printStackTrace();
        }
        // Retorna true (se encontrou usuário) ou false (se não encontrou)
        return result;
    }

    // MÉTODO PRINCIPAL (main) — PONTO DE ENTRADA DO PROGRAMA
    public static void main(String[] args) {

        // o método main não estava presente, e o Java não tinha ponto de entrada
        // para iniciar a aplicação, gerando a mensagem "Main method not found in the file".

        // Cria um novo objeto da classe User para acessar os métodos
        User usuario = new User();

        // Chama o método verificarUsuario, passando login e senha fixos
        boolean resultado = usuario.verificarUsuario("lopes", "123");

        // Exibe o resultado da verificação no console
        if (resultado) {
            System.out.println("Login realizado com sucesso! Bem-vindo, " + usuario.nome);
        } else {
            System.out.println("Usuário ou senha inválidos.");
        }
    }
}

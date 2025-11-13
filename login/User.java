// Pacote e importações
package login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

    // Conecta ao banco de dados
    public Connection conectarBD(){
        Connection conn = null;
        try{
            // Carrega o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver"); // Certifique-se de que o conector MySQL esteja no classpath
            // Define a URL de conexão
            String url = "jdbc:mysql://127.0.0.1/test?user=root&password=mudar123";
            // Estabelece a conexão
            conn = DriverManager.getConnection(url);
        }catch (ClassNotFoundException e) {
            // Erro ao carregar o driver
            System.out.println("Erro: Driver do MySQL não encontrado.");
            e.printStackTrace();
        }catch (Exception e) {
            // Erro na conexão
            System.out.println("Erro ao conectar ao banco.");
            e.printStackTrace();
        }
        return conn;
    }

    // Declaração de variáveis
    public String nome = "";
    public boolean result = false;

    // Verifica usuário no banco
    public boolean verificarUsuario(String login, String senha){
        String sql = "";
        Connection conn = conectarBD();
        
        // Verifica se a conexão falhou
        if (conn == null) {
            System.out.println("Conexão falhou.");
            return false;
        }

        // Monta o SQL
        sql += "select nome from usuarios where login = '" + login + "' and senha = '" + senha + "'";

        try{
            // Executa a consulta
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            // Se encontrado, armazena o nome
            if(rs.next()){
                result = true;
                nome = rs.getString("nome");
            }
        }catch (Exception e) {
            // Erro na consulta
            e.printStackTrace();
        }
        return result;
    }

    // Método principal
    public static void main(String[] args) {
        // Cria um objeto e verifica usuário
        User usuario = new User();
        boolean resultado = usuario.verificarUsuario("lopes", "123");

        // Exibe o resultado
        if (resultado) {
            System.out.println("Login realizado! Bem-vindo, " + usuario.nome);
        } else {
            System.out.println("Usuário ou senha inválidos.");
        }
    }
}

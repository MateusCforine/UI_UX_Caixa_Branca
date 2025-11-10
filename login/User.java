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
            // Carrega o driver JDBC do MySQL
            // O erro acontecia exatamente nesta linha abaixo:
            // ClassNotFoundException: com.mysql.cj.jdbc.Driver
            //
            // Isso significa que o Java não encontrou o driver do MySQL.
            // O motivo é que o arquivo 'mysql-connector-j.jar' não estava adicionado ao classpath do projeto.
            // Sem esse arquivo, o Java não consegue se comunicar com o banco.
            //
            // A correção foi baixar o MySQL Connector/J, colocar o .jar dentro da pasta 'lib'
            // e adicioná-lo ao classpath no VS Code.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Define a URL de conexão (endereço do banco, usuário e senha)
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";

            // Estabelece a conexão com o banco
            conn = DriverManager.getConnection(url);

        }catch (ClassNotFoundException e) {
            // Caso o driver não seja encontrado, exibe mensagem explicando o problema
            System.out.println("Erro: Driver do MySQL não encontrado. Adicione o arquivo mysql-connector-j.jar ao classpath.");
            e.printStackTrace();

        }catch (Exception e) {
            // Caso ocorra qualquer outro erro na tentativa de conexão
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

        // Segundo erro: NullPointerException acontecia aqui embaixo.
        // Quando o driver não era encontrado, o método conectarBD() retornava 'null'.
        // Mesmo assim, o código tentava usar 'conn.createStatement()', e como 'conn' era nulo,
        // o Java gerava um NullPointerException.
        // A correção foi incluir uma verificação: se a conexão for nula, o método encerra e mostra uma mensagem.
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

        // Esse foi o primeiro erro encontrado, pois o Java precisa de um ponto de entrada
        // para iniciar o programa. Sem ele, o erro apresentado foi:
        // "Main method not found in the file".
        // A correção foi adicionar esse método para permitir a execução do código.
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

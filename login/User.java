// Pacote da classe
package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

    // Faz a conexão com o banco
    public Connection conectarBD(){
        Connection conn = null;
        try{
            // Carrega o driver (erro se o conector não estiver no projeto)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // URL com usuário e senha (erro se qualquer info estiver incorreta)
            String url = "jdbc:mysql://127.0.0.1/test?user=root&password=mudar123";

            // Tenta conectar (erro se MySQL estiver desligado ou credenciais erradas)
            conn = DriverManager.getConnection(url);

        }catch (ClassNotFoundException e) {
            System.out.println("Driver do MySQL não encontrado.");
        }catch (Exception e) {
            System.out.println("Erro ao conectar ao banco.");
        }
        return conn;
    }

    // Variáveis usadas na verificação
    public String nome = "";
    public boolean result = false;

    // Consulta login e senha
    public boolean verificarUsuario(String login, String senha){
        String sql = "";
        Connection conn = conectarBD();
        
        // Erro se a conexão falhar
        if (conn == null) {
            System.out.println("Conexão falhou.");
            return false;
        }

        // SQL simples (erro se tabela ou campos não existirem)
        sql += "select nome from usuarios where login = '" + login + "' and senha = '" + senha + "'";

        try{
            // Envia o SQL (erro se o banco recusar)
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            // Achou o usuário
            if(rs.next()){
                result = true;
                nome = rs.getString("nome");
            }

        }catch (Exception e) {
            System.out.println("Erro ao executar a consulta.");
        }

        return result;
    }

    // Teste do login
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

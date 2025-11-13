1° Parte da Atividade - Teste Caixa Branca


package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User { //N1
    public Connection conectarBD(){ //N2
        Connection conn = null; //N3
        try { //N4
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();            |
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123"; | //N5
            conn = DriverManager.getConnection(url);                            |
        } catch (Exception e) { //N6
        }
        return conn;   //N7
    }

    public String nome = ""; 
    public boolean result = false; 

    public boolean verificarUsuario(String login, String senha){ //N8
        String sql = "";
        Connection conn = conectarBD(); //N9

        sql = "select nome from usuarios ";      |
        sql += "where login = '" + login + "'";  | //N10
        sql += " and senha = '" + senha + "'";   |

        try { //N11
            Statement st = conn.createStatement(); |
            ResultSet rs = st.executeQuery(sql);   | //N12
            if(rs.next()){ //N13
                result = true;                 |
                nome = rs.getString("nome");   | //N14
            }
        } catch (Exception e) { //N15
        }
        return result; //N16
    }
}


COMPLEXIDADE CICLOMÁTICA;

M = E - N + 2P
17 - 16 + 2 = 3

CAMINHOS BÁSICOS 

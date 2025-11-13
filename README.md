package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

    public Connection conectarBD(){
        Connection conn = null; //N1
        try { //N2
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();            |
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123"; | //N3
            conn = DriverManager.getConnection(url);                            |
        } catch (Exception e) { //N4
        }
        return conn;   //N5 
    }

    public String nome = ""; //N6
    public boolean result = false; //N7

    public boolean verificarUsuario(String login, String senha){ //N8
        String sql = "";N9
        Connection conn = conectarBD(); //N10





        sql = "select nome from usuarios ";      |
        sql += "where login = '" + login + "'";  | //N11
        sql += " and senha = '" + senha + "'";   |

        try { //N12
            Statement st = conn.createStatement(); |
            ResultSet rs = st.executeQuery(sql);   | //N13
            if(rs.next()){
                result = true;                 |
                nome = rs.getString("nome");   | //N14
            }
        } catch (Exception e) { //N15
        }
        return result; //N16
    }
}





CAMINHOS BÁSICOS

1) Conexão bem-sucedida e usuário encontrado (rs.next() verdadeiro)
Fluxo em que o banco conecta normalmente, a consulta retorna um registro e o login é concluído com sucesso:

N1 → N2 → N3 → N4 → N5 → N7 → N8 → N9 → N10 → N12 → N13 → N15 → N17 → N18 → N19 → N20 → N21 → N22

2) Conexão bem-sucedida e usuário não encontrado (rs.next() falso / sem registro)
Fluxo em que a conexão funciona, mas a consulta não acha o usuário, resultando em login inválido:

N1 → N2 → N3 → N4 → N5 → N7 → N8 → N9 → N11 → N16 → N17 → N18 → N19 → N20 → N21 → N22

3) Falha na conexão com o banco de dados
Fluxo em que a tentativa de conexão falha, a verificação é interrompida e o sistema retorna erro de login:

N1 → N2 → N3 → N6 → N7 → N8 → N9 → N10 → N11 → N16 → N17 → N18 → N19 → N20 → N21 → N22


COMPLEXIDADE CICLOMÁTICA;

Cálculo resumido da Complexidade Ciclomática

𝑉(𝐺)=𝐸−𝑁+2V(G)=E−N+2

E = 22 (arestas)
N = 22 (nós)

𝑉(𝐺)=22−22+2=2

Outra forma (pela quantidade de decisões):

Há 3 decisões (if)
V(G)=3+1=4

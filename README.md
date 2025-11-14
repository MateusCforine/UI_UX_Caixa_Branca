Após corrigir os erros no código, como a falta de conexão com o banco de dados, fiz as alterações necessárias. Abaixo está o resultado no terminal:

<img width="600" height="197" alt="image" src="https://github.com/user-attachments/assets/4da253b3-01f6-49fb-ae66-61e4e7b8413d" />

Link planilha Caixa Branca, aba Caixa Branca (Estático):

<img width="753" height="636" alt="image" src="https://github.com/user-attachments/assets/b18b84c4-ca4c-40ed-862c-e4fc5d8fcd6d" />


https://docs.google.com/spreadsheets/d/1-RhivEndmHv_hbaumLCJYW6y8HsG5Tzn/edit?usp=sharing&ouid=108784636124368837174&rtpof=true&sd=true

Para acessar o código corrigido, esse é o caminho: Acesse o repositório UI_UX_Caixa_Branca > Login > User.java

    
    
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

NOTAÇÃO DE GRAFO DE FLUXO;

<img width="1024" height="768" alt="N" src="https://github.com/user-attachments/assets/dcbacc0e-43b9-4893-8586-572a3a62d8bd" />


COMPLEXIDADE CICLOMÁTICA;

M = E - N + 2P

17 - 16 + 2 = 3


CAMINHOS BÁSICOS 

1 - Quando a conexão e o IF estão certos

N1 - N2 - N3 - N4 - N5 - N7 - N8 - N9 - N10 - N11 - N12 - N13 - N14 - N16

2 - Quando a conexão está certa e o IF da erro 

N1 - N2 - N3 - N4 - N5 - N7 -N8 - N9 - N10 - N11 - N12 - N15 - N16

3 - Quando a conexão e o IF da erro 

N1 - N2 - N3 - N4 - N5 - N6 - N7 - N8 - N9 - N10 - N11 - N12 - N15 - N16

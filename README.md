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

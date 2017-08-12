# Agenda de Atividades feita com Swing e PostgreSQL
Aplicação desenvolvida como trabalho de conclusão da matéria de Programação II, da Universidade Estadual do Piauí, Campus Parnaíba.

## Como utilizar:

Para utilizar a aplicação, você precisa ter o PostgreSQL instalado na sua maquina. Caso não tenha, acesse o site do ![PostgreSQL](https://www.postgresql.org/), e acesse documentação para saber como instalar.

### Criando as tabelas:

Abra o postgres e cria um banco de dados chamado "agenda", como no exemplo a seguir.

![Criando Banco](https://github.com/Werberth/Agenda-com-Swing-e-PostgreSQL/raw/master/resources/criando_banco.png)

Em seguinda, abra o banco "agenda" e crie as tabelas necessárias, com os seguintes comandos:

![Criando Tabelas](https://github.com/Werberth/Agenda-com-Swing-e-PostgreSQL/raw/master/resources/create_tables.png)

O esquema final do banco "agenda", será o seguinte:

![Tabelas](https://github.com/Werberth/Agenda-com-Swing-e-PostgreSQL/raw/master/resources/tabelas.png)

### Executando a aplicação

Primeiramente efetue o clone do repositorio.

Em seguinda, para permitir a conexão entre o banco de dados e a aplicação, é necessario que o Driver JDBC esteja instalado em sua maquina.
Caso não esteja, basta efetuar o download do arquivo ![postgresql-42.1.4.jar](https://jdbc.postgresql.org/download/postgresql-42.1.4.jar), copia-lo para pasta */usr/share/java/* e depois inserir o seguinte comando no terminal.

![Export Class](https://github.com/Werberth/Agenda-com-Swing-e-PostgreSQL/raw/master/resources/export_class.png)

Feito isso, agora é só compilar a classe principal Main.java da aplicação (aconselho a compilar classe por classe, para evitar erros do tipo *NullPointerException*), executá-la, e pronto, tudo vai ta funcionando!

![TelaInicial](https://github.com/Werberth/Agenda-com-Swing-e-PostgreSQL/raw/master/resources/tela_de_login.png)

**OBS:** Inicialmente não haverá nenhum usuário cadastrado no banco de dados, clique no botão "Cadastre-se" e cadastre um usuário antes de efetuar o primeiro Login.

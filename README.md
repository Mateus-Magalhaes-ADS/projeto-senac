# 📚 Sistema de Gestão Acadêmica — SENAC EAD.
### Projeto Integrador — 2ª Entrega | TADS / TSI



## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Tecnologias Utilizadas](#%EF%B8%8F-tecnologias-utilizadas)
- [Pré-requisitos](#-pré-requisitos)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Configuração do Banco de Dados (pgAdmin)](#%EF%B8%8F-configuração-do-banco-de-dados-pgadmin)
- [Configuração do Backend](#%EF%B8%8F-configuração-do-backend)
- [Configuração do Frontend](#-configuração-do-frontend)
- [Testando a API com Postman](#-testando-a-api-com-postman)
- [Endpoints da API](#-endpoints-da-api)
- [Credenciais de Acesso](#-credenciais-de-acesso)
- [Problemas Comuns](#-problemas-comuns)


---

## 📖 Sobre o Projeto

Sistema web de gestão acadêmica desenvolvido como Prova de Conceito (PoC) para a 2ª Entrega do Projeto Integrador do curso de TADS/TSI — SENAC EAD.

O sistema permite o gerenciamento de **Alunos**, **Cursos** e **Matrículas**, com autenticação via OAuth2 (JWT), controle de acesso por perfis (ADMIN e USER) e integração com banco de dados PostgreSQL.

---

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 21**
- **Spring Boot 3.3.4**
- **Spring Security + OAuth2 Authorization Server** (JWT)
- **Spring Data JPA + Hibernate**
- **PostgreSQL** (banco de dados relacional)
- **Maven** (gerenciador de dependências)

### Frontend
- **HTML5 / CSS3 / JavaScript** (puro, sem framework)

### Ferramentas
- **pgAdmin 4** — interface gráfica para o PostgreSQL
- **Postman** — testes de API REST
- **Git / GitHub** — controle de versão

---

## ✅ Pré-requisitos

Antes de começar, instale os seguintes programas no seu computador:

| Ferramenta | Versão Mínima | Link para Download |
|---|---|---|
| Java JDK | 21 | https://adoptium.net |
| Maven | 3.9+ | https://maven.apache.org/download.cgi |
| PostgreSQL | 15+ | https://www.postgresql.org/download |
| pgAdmin 4 | 7+ | https://www.pgadmin.org/download (geralmente instalado junto com o PostgreSQL) |
| Postman | qualquer | https://www.postman.com/downloads |
| Git | qualquer | https://git-scm.com/downloads |

### Verificando se o Java está instalado

Abra o terminal (CMD ou PowerShell no Windows) e execute:

```bash
java -version
```

A saída esperada é algo como:
```
openjdk version "21.0.x" ...
```

Se não aparecer, volte ao link acima e instale o JDK 21.

### Verificando se o Maven está instalado

```bash
mvn -version
```

> **Dica:** No Windows, após instalar o Maven, pode ser necessário reiniciar o terminal para que o comando seja reconhecido.

---

## 📁 Estrutura do Projeto

```
projeto-senac-master/
│
├── backend/                        # API REST em Spring Boot
│   ├── src/
│   │   └── main/
│   │       ├── java/com/demo/senac/
│   │       │   ├── config/         # Configurações de segurança OAuth2 e CORS
│   │       │   ├── controller/     # Endpoints REST (Aluno, Curso, Matrícula)
│   │       │   ├── dto/            # Objetos de transferência de dados
│   │       │   ├── entities/       # Entidades JPA (tabelas do banco)
│   │       │   ├── repositories/   # Acesso ao banco de dados
│   │       │   └── services/       # Regras de negócio
│   │       └── resources/
│   │           ├── application.properties   # Configurações do banco e segurança
│   │           └── data.sql                 # Script com dados iniciais
│   └── pom.xml                     # Dependências do projeto
│
└── front/                          # Frontend estático
    ├── index.html                  # Página principal
    ├── login.html                  # Tela de login
    ├── dashboard.html              # Dashboard após login
    └── style.css                   # Estilos globais
```

---

## 🗄️ Configuração do Banco de Dados (pgAdmin)

### Passo 1 — Abrir o pgAdmin

1. Abra o **pgAdmin 4** no seu computador (ele fica disponível no menu Iniciar após a instalação do PostgreSQL).
2. Ao abrir, ele pedirá a **senha master** que você definiu durante a instalação do pgAdmin.

### Passo 2 — Conectar ao Servidor PostgreSQL

No painel esquerdo do pgAdmin:

1. Expanda a seção **"Servers"**.
2. Clique com o botão direito em **"PostgreSQL 15"** (ou a versão que você instalou).
3. Clique em **"Connect Server"**.
4. Digite a senha do usuário `postgres` que você criou durante a instalação.

> **Atenção:** O usuário padrão do PostgreSQL é `postgres`. Lembre-se da senha que você definiu na instalação — ela será usada aqui e no `application.properties`.

### Passo 3 — Criar o Banco de Dados

1. No painel esquerdo, clique com o botão direito em **"Databases"**.
2. Selecione **"Create" → "Database..."**.
3. No campo **"Database"**, digite exatamente:

```
senac
```

4. Deixe o campo **"Owner"** como `postgres`.
5. Clique em **"Save"**.

O banco `senac` aparecerá na lista de Databases.

### Passo 4 — Verificar as configurações do application.properties

Abra o arquivo `backend/src/main/resources/application.properties` e confirme se as configurações batem com a sua instalação:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/senac
spring.datasource.username=postgres
spring.datasource.password=teste
```

> **Importante:** Se a sua senha do PostgreSQL for diferente de `teste`, altere o valor de `spring.datasource.password` para a senha correta.

### Passo 5 — Carregar os dados iniciais (data.sql)

O projeto já possui o arquivo `data.sql` com todos os dados de exemplo (usuários, alunos, cursos e matrículas). Ele é carregado automaticamente pelo Spring Boot na primeira execução quando configurado corretamente.

Para forçar a carga dos dados iniciais, siga estes passos:

1. Abra o arquivo `backend/src/main/resources/application.properties`.
2. **Comente** a linha `spring.sql.init.mode=never` e **descomente** a linha `spring.sql.init.mode=always`:

```properties
# Antes (desativar esta linha):
#spring.sql.init.mode=always

# Depois (ativar esta linha):
spring.sql.init.mode=always

# E comentar a outra:
#spring.sql.init.mode=never
```

3. **Execute o backend uma vez** (veja a seção seguinte).
4. Após a execução, **reverta** essa mudança (volte para `never`) para não duplicar os dados nas próximas inicializações.

### Passo 6 — Visualizar as tabelas no pgAdmin

Após iniciar o backend, as tabelas serão criadas automaticamente pelo Hibernate. Para visualizá-las:

1. No pgAdmin, expanda: **Databases → senac → Schemas → public → Tables**.
2. Você verá as tabelas: `tb_aluno`, `tb_curso`, `tb_matricula`, `tb_role`, `tb_user`, `tb_user_role`.
3. Para ver os dados de uma tabela, clique com o botão direito nela e selecione **"View/Edit Data" → "All Rows"**.

---

## ⚙️ Configuração do Backend

### Passo 1 — Clonar o repositório

```bash
git clone https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git
cd projeto-senac-master/backend
```

Ou, se já tiver os arquivos localmente, apenas navegue até a pasta `backend`:

```bash
cd caminho/para/projeto-senac-master/backend
```

### Passo 2 — Verificar o application.properties

Confirme que o arquivo `src/main/resources/application.properties` está assim:

```properties
# Conexão com o banco PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/senac
spring.datasource.username=postgres
spring.datasource.password=teste         # ← altere para sua senha

# Configuração do Hibernate (cria/atualiza tabelas automaticamente)
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Controle do data.sql (mude para "always" apenas na primeira execução)
spring.sql.init.mode=never
spring.jpa.defer-datasource-initialization=true

# Configurações de segurança OAuth2
security.client-id=myclientid
security.client-secret=myclientsecret
security.jwt.duration=86400

# CORS — origens permitidas para o frontend
cors.origins=http://localhost:3000,http://localhost:4200,http://localhost:5500,http://127.0.0.1:5500
```

### Passo 3 — Instalar as dependências e compilar

Dentro da pasta `backend`, execute:

```bash
mvn clean install
```

> Esse comando baixa todas as dependências do Maven e compila o projeto. Pode levar alguns minutos na primeira vez, pois precisa baixar as bibliotecas da internet.

### Passo 4 — Iniciar o servidor

```bash
mvn spring-boot:run
```

Ou, se preferir usar o executável Maven Wrapper que já vem no projeto:

```bash
# No Linux/Mac:
./mvnw spring-boot:run

# No Windows:
mvnw.cmd spring-boot:run
```

### Passo 5 — Confirmar que o servidor está rodando

Quando o servidor iniciar com sucesso, você verá no terminal uma mensagem parecida com:

```
Started DemoApplication in X.XXX seconds (process running for X.XXX)
```

O backend estará disponível em:
```
http://localhost:8080
```

> **Erro comum:** Se aparecer `Port 8080 already in use`, significa que outra aplicação está usando a porta 8080. Encerre-a ou adicione `server.port=8081` no `application.properties`.

---

## 🌐 Configuração do Frontend

O frontend é composto por arquivos HTML estáticos que se comunicam com o backend via JavaScript (fetch API).

### Opção 1 — Abrir diretamente pelo navegador

Navegue até a pasta `front/` e abra o arquivo `index.html` diretamente no navegador (duplo clique).

> **Atenção:** Algumas funcionalidades de login e requisições à API podem não funcionar corretamente abrindo o arquivo diretamente devido a restrições de CORS do navegador. Prefira a Opção 2.

### Opção 2 — Usar o Live Server do VS Code (recomendado)

1. Instale o **VS Code**: https://code.visualstudio.com
2. Instale a extensão **Live Server** (de Ritwick Dey) no VS Code.
3. Abra a pasta `front/` no VS Code.
4. Clique com o botão direito no arquivo `index.html` e selecione **"Open with Live Server"**.
5. O frontend abrirá em: `http://127.0.0.1:5500`

> Essa URL já está liberada no CORS do backend (`cors.origins`).

---

## 🧪 Testando a API com Postman

### Passo 1 — Instalar e abrir o Postman

Baixe em https://www.postman.com/downloads, instale e abra o Postman.

### Passo 2 — Criar uma Collection

1. No Postman, clique em **"Collections"** no painel esquerdo.
2. Clique em **"+"** ou **"New Collection"**.
3. Dê o nome: `SENAC - Projeto Integrador`.
4. Clique em **"Create"**.

### Passo 3 — Obter o Token de Acesso (Login OAuth2)

Antes de usar os endpoints protegidos, é necessário obter um **token JWT**.

1. Dentro da sua Collection, clique em **"Add a request"**.
2. Configure a requisição:

| Campo | Valor |
|---|---|
| Método | `POST` |
| URL | `http://localhost:8080/oauth2/token` |
| Nome | `🔐 Login - Obter Token` |

3. Vá na aba **"Body"**.
4. Selecione **"x-www-form-urlencoded"**.
5. Adicione os seguintes campos (clique em cada linha para adicionar):

| Key | Value |
|---|---|
| `grant_type` | `password` |
| `username` | `admin@senac.com` |
| `password` | `123456` |

6. Vá na aba **"Authorization"**.
7. Em **"Auth Type"**, selecione **"Basic Auth"**.
8. Preencha:

| Campo | Valor |
|---|---|
| Username | `myclientid` |
| Password | `myclientsecret` |

9. Clique em **"Send"**.

A resposta será parecida com:

```json
{
    "access_token": "eyJraWQiOiJlYzI1NiIsInR5cCI6IkpXVCIsImFsZyI6IkVTMjU2In0...",
    "token_type": "Bearer",
    "expires_in": 86400
}
```

10. **Copie** o valor do campo `access_token` — ele será usado em todas as outras requisições.

### Passo 4 — Configurar o Token nas requisições

Para não precisar colar o token em cada requisição manualmente, configure-o na **Collection**:

1. Clique com o botão direito na Collection `SENAC - Projeto Integrador`.
2. Selecione **"Edit"**.
3. Vá na aba **"Authorization"**.
4. Em **"Auth Type"**, selecione **"Bearer Token"**.
5. No campo **"Token"**, cole o token copiado no passo anterior.
6. Clique em **"Save"**.

Agora, em cada nova requisição, vá em **"Authorization"** e selecione **"Inherit auth from parent"** — o token será aplicado automaticamente.

### Passo 5 — Testar o primeiro endpoint

Crie uma nova requisição dentro da Collection:

| Campo | Valor |
|---|---|
| Método | `GET` |
| URL | `http://localhost:8080/alunos` |
| Nome | `📋 Listar Todos os Alunos` |

Na aba **"Authorization"**, selecione **"Inherit auth from parent"** e clique em **"Send"**.

A resposta deve retornar a lista de alunos em JSON com status `200 OK`.

---

## 📡 Endpoints da API

A URL base é: `http://localhost:8080`

Todos os endpoints marcados com 🔒 requerem o token Bearer no cabeçalho da requisição.

---

### 🔐 Autenticação

| Método | URL | Descrição | Auth |
|---|---|---|---|
| `POST` | `/oauth2/token` | Gera o token JWT (login) | Basic (client_id/secret) |

**Body (x-www-form-urlencoded):**
```
grant_type=password
username=admin@senac.com
password=123456
```

---

### 👨‍🎓 Alunos — `/alunos`

| Método | URL | Descrição | Perfil |
|---|---|---|---|
| `GET` | `/alunos` | Lista todos os alunos | 🔒 ADMIN |
| `GET` | `/alunos/{id}` | Busca aluno por ID | 🔒 ADMIN, USER |
| `POST` | `/alunos` | Cadastra novo aluno | Público |
| `PUT` | `/alunos/{id}` | Atualiza dados do aluno | 🔒 ADMIN, USER |
| `DELETE` | `/alunos/{id}` | Remove aluno | 🔒 ADMIN |

**Exemplo de Body para POST/PUT (JSON):**
```json
{
  "nome": "Novo Aluno",
  "cpf": "999.888.777-66",
  "email": "novo.aluno@email.com",
  "telefone": "81988880000",
  "senha": "123456",
  "endereco": "Rua Exemplo, 123"
}
```

---

### 📚 Cursos — `/cursos`

| Método | URL | Descrição | Perfil |
|---|---|---|---|
| `GET` | `/cursos` | Lista todos os cursos | 🔒 ADMIN, USER |
| `GET` | `/cursos/{id}` | Busca curso por ID | 🔒 ADMIN, USER |
| `POST` | `/cursos` | Cadastra novo curso | 🔒 ADMIN |
| `PUT` | `/cursos/{id}` | Atualiza curso | 🔒 ADMIN |
| `DELETE` | `/cursos/{id}` | Remove curso | 🔒 ADMIN |

**Exemplo de Body para POST/PUT (JSON):**
```json
{
  "nome": "Node.js Avançado",
  "descricao": "Desenvolvimento backend com Node.js",
  "duracao": "3 meses",
  "modalidade": "Online",
  "valorMensalidade": 175.00
}
```

---

### 📝 Matrículas — `/matriculas`

| Método | URL | Descrição | Perfil |
|---|---|---|---|
| `GET` | `/matriculas` | Lista todas as matrículas | 🔒 ADMIN |
| `GET` | `/matriculas/{id}` | Busca matrícula por ID | 🔒 ADMIN, USER |
| `GET` | `/matriculas/aluno/{alunoId}` | Matrículas de um aluno | 🔒 ADMIN, USER |
| `GET` | `/matriculas/curso/{cursoId}` | Matrículas de um curso | 🔒 ADMIN |
| `POST` | `/matriculas` | Cria nova matrícula | 🔒 ADMIN, USER |
| `PUT` | `/matriculas/{id}` | Atualiza matrícula | 🔒 ADMIN, USER |
| `DELETE` | `/matriculas/{id}` | Remove matrícula | 🔒 ADMIN |

**Exemplo de Body para POST/PUT (JSON):**
```json
{
  "dataMatricula": "2026-04-13",
  "status": "ATIVA",
  "alunoId": 1,
  "cursoId": 2
}
```

---

## 🔑 Credenciais de Acesso

### Usuário Administrador

| Campo | Valor |
|---|---|
| E-mail | `admin@senac.com` |
| Senha | `123456` |
| Perfil | `ROLE_ADMIN` + `ROLE_USER` |

### Usuários Alunos (todos com a mesma senha)

| Nome | E-mail | Senha |
|---|---|---|
| Caio Silva | `caio.silva@email.com` | `123456` |
| Maria Souza | `maria.souza@email.com` | `123456` |
| João Pereira | `joao.pereira@email.com` | `123456` |
| Ana Lima | `ana.lima@email.com` | `123456` |
| Pedro Santos | `pedro.santos@email.com` | `123456` |

### Credenciais OAuth2 (para o Postman)

| Campo | Valor |
|---|---|
| Client ID | `myclientid` |
| Client Secret | `myclientsecret` |

---


## ❗ Problemas Comuns

**Erro: `Connection refused` ao iniciar o backend**
→ O PostgreSQL não está em execução. Abra o pgAdmin e verifique se o servidor está conectado, ou reinicie o serviço do PostgreSQL.

**Erro: `password authentication failed for user "postgres"`**
→ A senha no `application.properties` está incorreta. Atualize o campo `spring.datasource.password` com a senha correta do seu PostgreSQL.

**Erro 401 no Postman**
→ O token expirou ou não foi enviado. Gere um novo token pelo endpoint `/oauth2/token` e atualize na Collection.

**Erro 403 no Postman**
→ O usuário logado não tem permissão para acessar aquele endpoint (ex.: um USER tentando acessar um endpoint de ADMIN). Faça login com `admin@senac.com`.

**Tabelas não criadas no banco**
→ Verifique se o banco `senac` foi criado no pgAdmin e se as configurações de conexão no `application.properties` estão corretas.

---

*Projeto desenvolvido para fins acadêmicos — SENAC EAD | 2026*

# 📚 RecoBook

Sistema de recomendação de livros que registra leituras dos usuários e utiliza inteligência artificial (Google Gemini) para sugerir novas leituras baseadas no histórico pessoal.

## 🚀 Sobre o Projeto

O RecoBook é uma aplicação Spring Boot que permite aos usuários:
- Registrar livros que leram
- Avaliar suas leituras
- Receber recomendações personalizadas de novos livros usando IA
- Gerenciar seu perfil e histórico de leituras

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.5**
- **Spring Security** - Autenticação e autorização
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL** - Banco de dados
- **Flyway** - Migração de banco de dados
- **JWT (Auth0)** - Tokens de autenticação
- **Lombok** - Redução de boilerplate
- **WebFlux** - Programação reativa para integração com APIs externas
- **Google Gemini API** - IA para recomendações
- **Docker** - Containerização
- **Maven** - Gerenciamento de dependências

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- PostgreSQL 16+
- Docker (opcional)
- Chave da API do Google Gemini

## 🔧 Configuração do Ambiente

### 1. Clone o repositório
```bash
git clone <url-do-repositorio>
cd RecoBook
```

### 2. Configuração do Banco de Dados

#### Opção A: Usando Docker (Recomendado)
```bash
docker-compose up -d
```

#### Opção B: PostgreSQL Local
1. Instale o PostgreSQL
2. Crie um banco de dados chamado `RecoBookDb`
3. Configure as credenciais no arquivo `application.yml`

### 3. Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis:

```env
# Banco de Dados
DATABASE_URL=jdbc:postgresql://localhost:5431/RecoBookDb
DATABASE_USERNAME=postgres
DATABASE_PASSWORD=postgres

# Google Gemini API
GMN_KEY=sua_chave_da_api_gemini

# JWT Secret
SECRET_HASH=sua_chave_secreta_jwt_super_segura
```

### 4. Executar a Aplicação

```bash
# Compilar o projeto
mvn clean compile

# Executar os testes
mvn test

# Executar a aplicação
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 📖 API Endpoints

### Autenticação
- `POST /user/register` - Registrar novo usuário
- `POST /user/login` - Fazer login e obter token JWT

### Usuário
- `GET /user/details` - Obter dados do usuário logado
- `PUT /user/update` - Atualizar dados do usuário logado
- `DELETE /user/delete` - Deletar conta do usuário logado

### Livros
- `POST /books/register` - Registrar novo livro lido
- `GET /books/my-books` - Listar livros do usuário logado
- `GET /books/details/{id}` - Obter detalhes de um livro específico
- `PUT /books/update/{id}` - Atualizar dados de um livro
- `DELETE /books/delete/{id}` - Deletar um livro

### Recomendações IA
- `GET /api/generate_book` - Gerar recomendações de livros usando IA

## 🔐 Autenticação

O sistema utiliza JWT (JSON Web Token) para autenticação. Após fazer login, inclua o token no header das requisições:

```
Authorization: Bearer <seu_token_jwt>
```

## 📊 Estrutura do Banco de Dados

### Tabela `users`
- `id` - ID único do usuário
- `first_name` - Primeiro nome
- `last_name` - Sobrenome
- `birth_date` - Data de nascimento
- `email` - Email (usado para login)
- `password` - Senha criptografada

### Tabela `books`
- `id` - ID único do livro
- `name` - Nome do livro
- `genre` - Gênero literário
- `date_start` - Data de início da leitura
- `date_end` - Data de término da leitura
- `evaluation` - Avaliação do livro
- `user_id` - Referência ao usuário proprietário

## 🤖 Integração com IA

O sistema utiliza a API do Google Gemini para gerar recomendações personalizadas baseadas no histórico de leituras do usuário. A IA analisa:
- Gêneros preferidos
- Padrões de avaliação
- Frequência de leitura
- Período de leitura dos livros

## 🐳 Docker

### Executar com Docker Compose
```bash
# Subir apenas o banco de dados
docker-compose up -d postgres

# Subir toda a aplicação (quando disponível)
docker-compose up -d
```

### Build da aplicação
```bash
# Build da imagem Docker
docker build -t recobook .

# Executar container
docker run -p 8080:8080 recobook
```

## 🧪 Testes

```bash
# Executar todos os testes
mvn test

# Executar testes com relatório de cobertura
mvn test jacoco:report
```

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/lest/dev/RecoBook/
│   │   ├── config/          # Configurações (Security, WebClient, etc.)
│   │   ├── controller/      # Controladores REST
│   │   ├── dto/            # Data Transfer Objects
│   │   ├── entity/         # Entidades JPA
│   │   ├── exception/      # Exceções customizadas
│   │   ├── mapper/         # Mappers para conversão de objetos
│   │   ├── repository/     # Repositórios JPA
│   │   ├── service/        # Lógica de negócio
│   │   └── validatoras/    # Validadores customizados
│   └── resources/
│       ├── application.yml # Configurações da aplicação
│       └── db/migration/   # Scripts de migração Flyway
└── test/                   # Testes unitários e de integração
```

## 🔒 Segurança

- Senhas são criptografadas usando BCrypt
- Validação de senhas fortes
- Autenticação JWT com expiração
- Validação de entrada com Bean Validation
- Proteção contra ataques comuns (CSRF, XSS)

## 🚀 Deploy

### Build para produção
```bash
mvn clean package -Pprod
```

### Executar JAR
```bash
java -jar target/RecoBook-0.0.1-SNAPSHOT.jar
```

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 📞 Suporte

Para dúvidas ou suporte, entre em contato através dos issues do GitHub.

---

**Desenvolvido com ❤️ usando Spring Boot e Google Gemini AI**

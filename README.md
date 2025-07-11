# LiterAlura 📚

Projeto de catálogo de livros que consome a API Gutendex e armazena os dados em um banco PostgreSQL. Faz parte do programa ONE.

## ⚠️ Atenção: Possíveis Erros

Este projeto pode apresentar alguns erros comuns durante a execução:

1. **Problemas com o PostgreSQL**:
   - Falha na conexão com o banco de dados
   - Erros de autenticação
   - Banco de dados não encontrado

2. **Problemas com a API Gutendex**:
   - Limitação de requisições
   - Mudanças na estrutura da API
   - Timeout de conexão

3. **Problemas de compilação**:
   - Dependências não resolvidas
   - Versões incompatíveis de Java/Spring Boot

## 🛠️ Pré-requisitos

- Java 17+
- PostgreSQL 15+
- Maven 3.9+
- Conta no GitHub (para clonar o repositório)

## 🔧 Configuração

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/LiterAlura.git
```
## 🔧 Configure o banco de dados:
```bash
# Edite o application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=postgres
spring.datasource.password=sua_senha
```
## Execute a aplicação:
```bash
mvn spring-boot:run
```
## 🚀 Funcionalidades
- Busca de livros por título
- Listagem de autores
- Filtros por idioma e ano
- Top 10 livros mais baixados

## 🤔 Problemas Conhecidos
- Algumas buscas podem retornar resultados inesperados
- A API pode estar temporariamente indisponível
- Pode requerer ajustes nas configurações do PostgreSQL

## 💡 Solução de Problemas
- Verifique os logs da aplicação
- Confira a conexão com o banco de dados
- Teste a API Gutendex manualmente

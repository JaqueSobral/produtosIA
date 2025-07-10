# Spring Boot CRUD de Produtos #
Este projeto é uma API RESTful para gerenciamento de produtos, migrado de Node.js para Java com Spring Boot. Ele segue boas práticas de arquitetura em camadas
(Controller, Service, Repository, DTO, Model) e utiliza Spring Data JPA com banco H2 em memória.
---
## ✨ Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **H2 Database** (banco em memória)
- **Lombok**
- **Maven**
- **Spring Web**
- **Spring DevTools**
---
## 📁 Estrutura do Projeto
## shell
src/main/java
└── com
    └── example
        └── crudprodutos
            ├── controller      # Endpoints REST
            ├── dto            # Objetos de transferência de dados
            ├── model          # Entidades JPA
            ├── repository     # Interfaces Spring Data JPA
            └── service
                └── impl       # Implementações dos serviços
                --- ## 🔗 Endpoints da API
Método	Rota	Descrição
POST	/produtos	Cria um novo produto
GET	/produtos	Lista todos os produtos
GET	/produtos/{id}	Busca produto por ID
PUT	/produtos/{id}	Atualiza produto por ID
DELETE	/produtos/{id}	Remove produto por ID
🚀 Como Executar
Clone o repositório
bash
---
## git clone https://github.com/seu-usuario/seu-repo.git
cd seu-repo
Compile e execute a aplicação
Usando Maven:
bash
./mvnw spring-boot:run
Ou execute a classe principal (CrudProdutosApplication) pela sua IDE.
Acesse a API
API REST: http://localhost:8080/
Console H2: http://localhost:8080/h2-console
Usuário: sa
Senha: (em branco)
JDBC URL: jdbc:h2:mem:testdb
---
## 📦 Exemplo de Produto (JSON)
Criar Produto
json
{
  "nome": "Produto 1",
  "dataCriacao": "2024-06-01",
  "quantidadeDisponivel": 100
}
---
Resposta GET /produtos
json
[
  {
    "id": 1,
    "nome": "Produto 1",
    "dataCriacao": "2024-06-01",
    "quantidadeDisponivel": 100
  }
]
---
## ⚙️ Configurações
O campo id é gerado automaticamente.
Para alterar configurações do banco, edite o arquivo: src/main/resources/application.properties.
O projeto utiliza banco H2 apenas para desenvolvimento/testes. Para usar outro banco, ajuste o application.properties e a dependência no pom.xml.
👨‍💻 Autor
Jaqueline Sobral
📝 Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.
---

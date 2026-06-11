📚 API de Catálogo de Livros

API REST desenvolvida com Java + Spring Boot para gerenciamento de um catálogo de livros e autores.

O projeto permite realizar operações de cadastro, consulta, atualização e remoção de livros e autores, além de relacionar livros a seus respectivos autores.

---

🚀 Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Lombok

---

📌 Funcionalidades

👤 Autor

- ✅ Cadastrar autor
- ✅ Listar autores
- ✅ Buscar autor por ID
- ✅ Atualizar autor
- ✅ Deletar autor
- ✅ Retornar autor com lista de livros

📖 Livro

- ✅ Cadastrar livro vinculado a um autor
- ✅ Listar livros
- ✅ Buscar livro por ID
- ✅ Atualizar livro
- ✅ Deletar livro
- ✅ Retornar livro com dados do autor

---

🔗 Relacionamento

- Um Autor pode ter vários Livros ("@OneToMany")
- Um Livro pertence a um único Autor ("@ManyToOne")

---

🧱 Estrutura do Projeto

O projeto segue o padrão de arquitetura em camadas:

controller → service → repository → entity

- Controller: responsável pelos endpoints da API
- Service: regras de negócio
- Repository: comunicação com o banco de dados
- DTOs: transferência de dados (evita expor entidades diretamente)

---

📦 Exemplos de Endpoints

🔹 Autor

Método| Endpoint| Descrição
POST| /autores| Cadastrar autor
GET| /autores| Listar autores
GET| /autores/{id}| Buscar autor
PUT| /autores/{id}| Atualizar autor
DELETE| /autores/{id}| Deletar autor

---

🔹 Livro

Método| Endpoint| Descrição
POST| /livros| Cadastrar livro
GET| /livros| Listar livros
GET| /livros/{id}| Buscar livro
PUT| /livros/{id}| Atualizar livro
DELETE| /livros/{id}| Deletar livro

---

🧪 Exemplo de JSON

📌 Cadastro de Autor

{
  "nome": "Machado de Assis",
  "dataNascimento": "1839-06-21"
}

---

📌 Cadastro de Livro

{
  "titulo": "Dom Casmurro",
  "descricao": "Romance brasileiro clássico",
  "dataPublicacao": "1899-01-01",
  "autorId": "UUID_DO_AUTOR"
}

---

⚠️ Tratamento de Erros

A API utiliza tratamento global de exceções com "@ControllerAdvice", retornando:

- 404 → Recurso não encontrado
- 409 → Conflito (ex: autor já cadastrado)
- 400 → Dados inválidos

---

🔐 Boas práticas aplicadas

- Uso de DTOs para evitar exposição de entidades
- Separação de responsabilidades (camadas)
- Validação de dados
- Uso de exceptions customizadas
- Código organizado e de fácil manutenção

---

📈 Melhorias futuras

- 🔐 Implementação de autenticação com JWT
- 📄 Documentação com Swagger/OpenAPI
- 🐳 Docker para containerização
- ☁️ Deploy em ambiente cloud

---

👨‍💻 Autor

Desenvolvido por GLADYSON GABRIEL BARBOSA DE FREITAS
Linkedin: www.linkedin.com/in/gladyson-gabriel30
---

📌 Observação

Este projeto foi desenvolvido com foco em aprendizado e evolução como desenvolvedor backend Java.

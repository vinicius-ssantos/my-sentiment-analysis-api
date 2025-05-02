# 🧠 Análise de Sentimentos com Spring Boot + Hugging Face + Thymeleaf

Este projeto é uma aplicação web que analisa o sentimento de comentários usando um modelo de linguagem treinado via [Hugging Face](https://huggingface.co). Ele utiliza **Spring Boot WebFlux**, integração com a API de IA da Hugging Face, e uma interface simples feita com **Thymeleaf**.

---

## 🚀 Funcionalidades

- 🔍 Análise de sentimentos em tempo real via IA
- 🌐 Interface web com Thymeleaf
- ✅ Suporte reativo com Spring WebFlux
- 🤖 Modelo usado: `nlptown/bert-base-multilingual-uncased-sentiment`

---

## 🧪 Exemplo de uso

### Entrada:
```
O filme é bom
```

### Saída:
```
Sentimento: Positivo
Label dominante: 4 stars
Score: 0.44
```

---

## 🧰 Tecnologias

- Java 17
- Spring Boot 3.x
- Spring WebFlux
- Thymeleaf
- WebClient (para chamadas HTTP reativas)
- Hugging Face API
- Maven

---

## ⚙️ Como rodar localmente

### Pré-requisitos:

- Java 17+
- Maven
- Conta no [huggingface.co](https://huggingface.co) com token de API

### Clone o repositório:

```bash
git clone https://github.com/seu-usuario/my-sentiment-analysis-api.git
cd my-sentiment-analysis-api
```

### Configure o token Hugging Face:

No arquivo `src/main/resources/application.properties`, adicione:

```
huggingface.api.token=SEU_TOKEN_AQUI
```

### Compile e execute:

```bash
mvn clean spring-boot:run
```

Acesse: [http://localhost:8080](http://localhost:8080)

---

## 📁 Estrutura do projeto

```
src/
├── main/
│   ├── java/com/.../controller/WebController.java
│   ├── java/com/.../service/SentimentAnalysisService.java
│   ├── java/com/.../client/HuggingFaceClient.java
│   ├── resources/templates/index.html
│   └── resources/application.properties
└── test/
```

---

## 📦 API REST (opcional)

Você também pode usar via REST API:

```http
POST /api/sentiment
Content-Type: application/json

{
  "comment": "esse produto é maravilhoso!"
}
```

---

## 👨‍💻 Autor

- Vinicius Santos
- [LinkedIn](https://www.linkedin.com/)
- [GitHub](https://github.com/)

---

## 📝 Licença

Este projeto está sob a licença MIT.

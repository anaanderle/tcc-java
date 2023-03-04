## Execução da API

- Configure o arquivo application.yml localizado em `api/src/main/resources/application.yml` como no exemplo:

```yml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/tcc_java
    username: tcc_java (seu user)
    password: tcc_java (sua senha)
```

- Rodar os arquivos de schema e insert disponibilizados na pasta `/data` para criar e popular as tabelas do banco de dados.
- Rodar a api no Intellij utilizando a JDK 11.
- A api roda no endereço `localhost:8081`.

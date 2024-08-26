Claro! Aqui está a documentação revisada, com a seção de dependências formatada de acordo com suas instruções:

---

# Documentação do Projeto de Automação de Testes

## Descrição do Projeto

Este projeto é uma automação de testes utilizando **RestAssured** e **Cucumber** com **Java 21**. O objetivo é garantir a qualidade das funcionalidades da aplicação através de testes automatizados de API. O projeto foi criado por **Luana** para a empresa **Accenture**.

## Passos dos Testes de API

1. **Criar um Usuário**
   - Envia uma solicitação para criar um novo usuário.

2. **Criar um Token de Autenticação**
   - Solicita um token de autenticação utilizando as credenciais do usuário criado.

3. **Validar o Token**
   - Verifica se o token gerado é válido.

4. **Listar Livros Disponíveis**
   - Usa o token válido para listar os livros disponíveis no sistema.

5. **Reservar Dois Livros**
   - Reserva dois livros disponíveis usando o token.

6. **Listar Detalhes do Usuário**
   - Obtém detalhes do usuário criado e verifica os livros que foram reservados.

## Requisitos

Para executar este projeto, você precisará de:

- **Java 21**
- **Maven**
- **IDE Eclipse**
- **WebDriver** (ChromeDriver)
- **Postman** (para testes manuais de API)

## Como Executar os Testes

Para executar os testes, siga os seguintes passos:

1. Abra a IDE Eclipse.
2. Navegue até o arquivo **AutomationDemoQA.feature**.
3. Clique com o botão direito no arquivo e selecione **Run As** > **Cucumber Test**.

## Dependências Principais

- **RestAssured**: Para realizar e validar requisições de API.
- **Cucumber**: Para suportar a execução de testes BDD (Behavior Driven Development).
- **JUnit**: Para executar e integrar os testes com o framework de teste.
- **Hamcrest**: Para criar asserções de teste legíveis e compreensíveis.
- **Selenium WebDriver**: Se o projeto incluir testes de UI (não aplicável aqui, mas útil em outros contextos).
- **AShot**: Para captura de screenshots em testes de UI (não aplicável aqui, mas útil em outros contextos).

## Clone do Repositório

Você pode clonar o repositório do projeto utilizando o seguinte comando:

```bash
git clone https://github.com/Luanninha/Desafio-Accenture
```

---

Se precisar de mais alguma coisa ou tiver outras solicitações, é só avisar!
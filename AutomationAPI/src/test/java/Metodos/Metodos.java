package Metodos;

import static io.restassured.RestAssured.given;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.response.Response;

public class Metodos {

    private Response response;
    private String token;
    private String userID;
    private WebDriver driver;

    // Configura o driver do Selenium.
    public Metodos() {
        System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    // Cria um novo usuário na API.
    public void criarUsuario() {
        response = given().log().all().contentType("application/json")
                .body("{\r\n" + "  \"userName\": \"AndreaGabrielaStellaMoreira\",\r\n"
                        + "  \"password\": \"Senha123!\"\r\n" + "}")
                .when().post("https://demoqa.com/Account/v1/User").then().log().all().extract().response();

        validateStatusCode(201); // Código esperado é 201 para criação
        userID = response.jsonPath().getString("userID");
        System.out.println("User ID: " + userID);
    }

    // Gera um token de acesso para o usuário criado.
    public void gerarTokenAcesso() {
        response = given().log().all().contentType("application/json")
                .body("{\r\n" + "  \"userName\": \"AndreaGabrielaStellaMoreira\",\r\n"
                        + "  \"password\": \"Senha123!\"\r\n" + "}")
                .when().post("https://demoqa.com/Account/v1/GenerateToken").then().log().all().extract().response();

        validateStatusCode(200); // Código esperado é 200 para sucesso
        token = response.jsonPath().getString("token");
        System.out.println("Token: " + token);
    }

    // Solicita a lista de livros disponíveis.
    public Response listarLivros() {
        response = given().log().all().when().get("https://demoqa.com/Bookstore/v1/Books").then().log().all().extract().response();
        validateStatusCode(200); // Código esperado é 200 para sucesso
        return response;
    }

    // Realiza a reserva de dois livros.
    public Response alugarLivros() {
        String book1 = "9781449331818";
        String book2 = "9781491904244";
        String payload = String.format("{ \"userId\": \"%s\", \"collectionOfIsbns\": [{ \"isbn\": \"%s\" }, { \"isbn\": \"%s\" }] }", userID, book1, book2);

        response = given().log().all()
            .contentType("application/json")
            .header("Authorization", "Bearer " + token)
            .body(payload)
            .when().post("https://demoqa.com/Bookstore/v1/Books")
            .then().log().all().extract().response();
        validateStatusCode(201); // Código esperado é 201 para criação
        return response;
    }

    // Solicita os detalhes do usuário criado.
    public Response detalhesUsuario() {
        response = given().log().all()
            .contentType("application/json")
            .header("Authorization", "Bearer " + token)
            .when().get("https://demoqa.com/Account/v1/User/" + userID)
            .then().log().all().extract().response();
        validateStatusCode(200); // Código esperado é 200 para sucesso
        return response;
    }

    // Obtém o token gerado.
    public String getToken() {
        return token;
    }

    // Valida o código de status da resposta.
    private void validateStatusCode(int expectedStatusCode) {
        if (response.statusCode() != expectedStatusCode) {
            throw new AssertionError("Código de status esperado: " + expectedStatusCode + " e recebido: " + response.statusCode());
        }
    }

    // Tira um print da tela.
    public void tirarPrint(String caminhoDoArquivo) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        byte[] imagem = screenshot.getScreenshotAs(OutputType.BYTES);
        // Código para salvar a imagem no caminho especificado
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get(caminhoDoArquivo), imagem);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    // Pausa a execução por um tempo especificado.
    public void pausar(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Fecha o driver do Selenium.
    public void fecharDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
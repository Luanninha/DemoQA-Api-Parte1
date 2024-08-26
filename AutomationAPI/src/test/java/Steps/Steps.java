package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Metodos.Metodos;
import io.restassured.response.Response;
import static org.junit.Assert.assertEquals;

public class Steps {

    private Metodos m = new Metodos(); // Instância abreviada da classe Metodos
    private Response response; // Variável para armazenar as respostas das requisições

    @Given("que eu crio um novo usuário")
    public void que_eu_crio_um_novo_usuário() {
        m.criarUsuario(); // Cria um novo usuário
    }

    @When("eu gero um token de acesso para o usuário criado")
    public void eu_gero_um_token_de_acesso_para_o_usuário_criado() {
        m.gerarTokenAcesso(); // Gera o token de acesso para o usuário
    }

    @Then("o token de acesso deve ser válido")
    public void o_token_de_acesso_deve_ser_válido() {
        String token = m.getToken(); // Recupera o token gerado
        assertEquals("Token não deve ser nulo", 200, token != null ? 200 : 500);
        System.out.println("Token de acesso válido: " + token);
    }

    @When("eu solicito a lista de livros disponíveis")
    public void eu_solicito_a_lista_de_livros_disponíveis() {
        response = m.listarLivros(); // Solicita a lista de livros disponíveis
    }

    @When("eu realizo a reserva de dois livros")
    public void eu_realizo_a_reserva_dos_livros() {
        response = m.alugarLivros(); // Realiza o aluguel dos livros
    }

    @When("eu solicito os detalhes do usuário criado")
    public void eu_solicito_os_detalhes_do_usuário_criado() {
        response = m.detalhesUsuario(); // Solicita os detalhes do usuário criado
    }

    @Then("os detalhes do usuário e livros reservados devem ser exibidos")
    public void os_detalhes_do_usuário_e_livros_reservados_devem_ser_exibidos() {
        assertEquals(200, response.statusCode()); // Verifica se os detalhes foram exibidos corretamente
        System.out.println("Detalhes do usuário e livros reservados: " + response.asString());

        // Tira um print da tela
        m.tirarPrint("caminho/para/screenshot.png"); 

        // Pausa a execução por 5 segundos
        m.pausar(5000);
        
        // Fecha o driver do Selenium
        m.fecharDriver();
    }
}

package br.com.estoque.service;

import br.com.estoque.entities.Produto;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ValidaValoresServiceTest {

    @InjectMocks
    ValidaValoresService validaValoresService;

    @BeforeClass
    public static void setup(){

        System.out.println("ValidaValoresServiceTest class");

    }

    @Before
    public void beforeEachClass(){

        System.out.println("Iniciando os testes");

    }

    @Test
    public void testValidaValores() throws Exception {

        Produto produto = new Produto("Produto Um", 123.4, "Teste para produto Um", 2, "Categoria Um");

        validaValoresService = new ValidaValoresService();
        boolean validaValoresInseridos = validaValoresService.validaValores(produto, new ControleProdutoService());

        Assert.assertTrue("Fantam valores para inserir o Produto", validaValoresInseridos);

    }

    @Test(expected = Exception.class)
    public void testeValidaValoresErro() throws Exception {

        Produto produtoSemInformacoes = new Produto("", 123.4, "", 2, "");

        validaValoresService = new ValidaValoresService();
        validaValoresService.validaValores(produtoSemInformacoes, new ControleProdutoService());

    }

    @AfterClass
    public static void end(){

        System.out.println("Finalizando os testes");

    }

}

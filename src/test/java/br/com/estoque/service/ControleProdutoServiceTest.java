package br.com.estoque.service;

import br.com.estoque.entities.Produto;
import org.junit.*;
import org.junit.runner.RunWith;
import java.util.HashMap;
import java.util.Map;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ControleProdutoServiceTest {

    @InjectMocks
    private ControleProdutoService controleProdutoService;

    @BeforeClass
    public static void setup(){

        System.out.println("ControleProdutoServiceTest class");

    }

    @Before
    public void beforeEachClass(){

        System.out.println("Iniciando os testes");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddProdutoAoEstoqueErro(){

        Produto produto = new Produto("Produto Um", 123.4, "Teste para produto Um", 2, "Categoria Um");
        Map<String, Produto> produtoMap = criaProdutos();

        controleProdutoService = new ControleProdutoService(produtoMap);
        controleProdutoService.addProdutoAoEstoque(produto);

    }

    @Test
    public void testAddProdutoAoEstoque(){

        Produto produto = new Produto("Novo Produto", 123.4, "Teste para produto Um", 2, "Categoria Um");
        Map<String, Produto> produtoMap = criaProdutos();

        int registrosSalvo = produtoMap.size();

        controleProdutoService = new ControleProdutoService(produtoMap);

        controleProdutoService.addProdutoAoEstoque(produto);

        Assert.assertEquals(registrosSalvo + 1, produtoMap.size());

    }

    @Test
    public void testListaProdutosNoEstoque(){

        Map<String, Produto> produtoMap = criaProdutos();

        controleProdutoService = new ControleProdutoService(produtoMap);
        int totalProdutosListados = controleProdutoService.listaProdutosNoEstoque();

        Assert.assertEquals(produtoMap.size(), totalProdutosListados);

    }

    @Test
    public void testProcuraProdutoPeloNome(){

        Map<String, Produto> produtoMap = criaProdutos();

        controleProdutoService = new ControleProdutoService(produtoMap);

        Assert.assertTrue("Produto não identificado", controleProdutoService.procuraProdutoPeloNome("Produto Um"));

    }

    @Test(expected = AssertionError.class)
    public void testProcuraProdutoPeloNomeErro(){

        Map<String, Produto> produtoMap = criaProdutos();

        controleProdutoService = new ControleProdutoService(produtoMap);

        Assert.assertTrue("Produto não identificado", controleProdutoService.procuraProdutoPeloNome("Produto Novo"));

    }

    public Map<String, Produto> criaProdutos(){

        Map<String, Produto> produtoMap = new HashMap<String, Produto>();

        Produto produtoParaTesteUm = new Produto("Produto Um", 123.4, "Teste para produto Um", 2, "Categoria Um");
        Produto produtoParaTesteDois = new Produto("Produto Dois", 144.0, "Teste para produto Dois", 4, "Categoria Dois");
        Produto produtoParaTesteTres = new Produto("Produto Tres", 52.6, "Teste para produto Tres", 2, "Categoria Tres");

        produtoMap.put(produtoParaTesteUm.getNome(), produtoParaTesteUm);
        produtoMap.put(produtoParaTesteDois.getNome(), produtoParaTesteDois);
        produtoMap.put(produtoParaTesteTres.getNome(), produtoParaTesteTres);

        return produtoMap;

    }

    @AfterClass
    public static void end(){

        System.out.println("Finalizando os testes");

    }


}

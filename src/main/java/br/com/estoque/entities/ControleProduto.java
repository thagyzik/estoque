package br.com.estoque.entities;

public interface ControleProduto {

    void addProdutoAoEstoque(Produto produto);
    int listaProdutosNoEstoque();
    boolean procuraProdutoPeloNome(String nome);

}

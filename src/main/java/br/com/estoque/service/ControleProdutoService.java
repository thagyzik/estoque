package br.com.estoque.service;

import br.com.estoque.entities.ControleProduto;
import br.com.estoque.entities.Produto;
import java.util.HashMap;
import java.util.Map;

public class ControleProdutoService implements ControleProduto {

    private Map<String, Produto> mapProdutos;

    public ControleProdutoService(){

        this.mapProdutos = new HashMap<String, Produto>();

    }

    public ControleProdutoService(Map<String, Produto> mapProdutos){

        this.mapProdutos = mapProdutos;

    }

    @Override
    public void addProdutoAoEstoque(Produto produto){

        // valida se produto não existe antes de inseri-lo novamente
        if (this.mapProdutos.get(produto.getNome()) != null){

            throw new IllegalArgumentException("Produto já esta inserido no estoque!");

        }

        this.mapProdutos.put(produto.getNome(), produto);
        System.out.println("Novo produto salvo: " + produto.toString());

    }

    @Override
    public int listaProdutosNoEstoque(){

        int i = 0;

        for (Map.Entry entryProduto : this.mapProdutos.entrySet()){

            System.out.println(i + " " + entryProduto.getValue());

            i += 1;

        }

        return i;

    }

    @Override
    public boolean procuraProdutoPeloNome(String nome) {

        String identificarProduto = null;

        // caso produto não seja identificado ou lista esteja vazia, retorna false
        if (!this.mapProdutos.isEmpty()) {

            for (Map.Entry entryProduto : this.mapProdutos.entrySet()) {

                // valida se existe produto exatamente com mesmo nome informado
                if (entryProduto.getKey().equals(nome)) {

                    identificarProduto = "Produto: " + nome + " - Preço: " + this.mapProdutos.get(nome).getPreco() + " - Descrição: " + this.mapProdutos.get(nome).getDescricao();

                    System.out.println(identificarProduto);

                    return true;

                }

            }

            return false;

        } else {

            return false;

        }

    }

}

package br.com.estoque.service;

import br.com.estoque.entities.Produto;

public class ValidaValoresService {

    // valida se não possui dados vazios ou nulls
    public boolean validaValores(Produto produto, ControleProdutoService controleProdutoService) throws Exception {

        if (produto.getNome() != null && !produto.getNome().equals("") &&
                produto.getDescricao() != null && !produto.getDescricao().equals("") &&
                produto.getCategoria() != null && !produto.getCategoria().equals("")){

            // envia produto para ser inserido ao estoque
            controleProdutoService.addProdutoAoEstoque(produto);

            System.out.println("Finalizada inserção: \n");

            return true;

        } else {

            throw new Exception("Faltam valores para inserir produto no estoque");

        }

    }

}

package br.com.estoque;

import br.com.estoque.entities.Produto;
import br.com.estoque.service.ControleProdutoService;
import br.com.estoque.service.ValidaValoresService;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EstoqueApplication {

    public static void main(String[] args) {

        int opcaoDoMenu = 0;

        ControleProdutoService controleProdutoService = new ControleProdutoService();

        System.out.println("Bem vindo ao controle de Produtos do Estoque.\n");

        do {

            try {

                Scanner scanner = new Scanner(System.in);
                System.out.println("\n Selecione a opção desejada! \n");
                System.out.println("1 - Salvar um novo produto no estoque.");
                System.out.println("2 - Listar os produto já salvos.");
                System.out.println("3 - Pesquisar um produto.");
                System.out.println("4 - SAIR.");
                opcaoDoMenu = scanner.nextInt();

                String seguirInsercao = "N";

                if (opcaoDoMenu == 1) {

                    do {

                        try {

                            Produto produto = new Produto();
                            ValidaValoresService validaValoresService = new ValidaValoresService();

                            // solicita dados do produto
                            System.out.println("Informe o preço do produto: ");
                            produto.setPreco(scanner.nextDouble());

                            scanner.nextLine();

                            System.out.println("Informe o nome do produto: ");
                            produto.setNome(scanner.nextLine());

                            System.out.println("Informe a quantidade deste produto: ");
                            produto.setQuantidade(scanner.nextInt());

                            scanner.nextLine();

                            System.out.println("Informe a descrição do produto: ");
                            produto.setDescricao(scanner.nextLine());

                            System.out.println("Informe a categoria do produto: ");
                            produto.setCategoria(scanner.nextLine());

                            // passa os valores recebidos para uma validação antes de salvar
                            validaValoresService.validaValores(produto, controleProdutoService);

                            // sempre verifica se cliente quer inserir novos produtos
                            System.out.println("Selecione S para inserir novos produtos ou N para finalizar");
                            seguirInsercao = scanner.nextLine();

                        } catch (InputMismatchException e) {

                            seguirInsercao = "N";
                            System.err.println("Preço ou Quantidade informado incorretamente, tente novamente!");

                        } catch (IllegalArgumentException e) {

                            System.err.println("Produto já inserido no estoque!");

                        } catch (Exception e) {

                            System.err.println(e.getMessage());

                        }

                    } while (!seguirInsercao.equalsIgnoreCase("N"));

                } else if (opcaoDoMenu == 2) {

                    // chama o metodo para imprimir todos os produtos e imprime o numero de produtos listados
                    int totalProdutosListados = controleProdutoService.listaProdutosNoEstoque();

                    System.out.println("Total processados: " + totalProdutosListados);

                } else if (opcaoDoMenu == 3) {

                    // solicita o nome do produto a ser listado somente preço e descrição
                    System.out.println("Qual nome do produto que deseja procurar? ");

                    scanner.nextLine();

                    boolean retornoProduto = controleProdutoService.procuraProdutoPeloNome(scanner.nextLine());

                    if (!retornoProduto) {

                        System.err.println("Produto não identificado no estoque!");

                    }

                } else if (opcaoDoMenu == 4) {

                    System.out.println("Fim! ");

                } else {

                    System.err.println("Opção incorreta, tente novamente!");

                }

            } catch (InputMismatchException e){

                System.err.println("Você deve informar um numero correspondendo ao menu!");

            }

        } while (opcaoDoMenu != 4);

    }

}

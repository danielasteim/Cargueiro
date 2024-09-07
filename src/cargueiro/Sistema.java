package cargueiro;

import java.util.Scanner;

public class Sistema {
    public static void main(String[] args) {
        // Criação de um scanner para ler a entrada do usuário
        Scanner entrada = new Scanner(System.in);

        // CONFIGURAÇÃO DO SISTEMA
        // Criando um avião com nome "aviao 1" e uma carga mínima de 500
        int voo = 1;
        String nome = "aviao " + voo;
        float cargaMinima = 500;
        Aviao aviaoCargueiro = new Aviao(nome, cargaMinima);

        // Criando compartimentos do avião
        // "Principal" aceita apenas carga do tipo "simples" e tem carga máxima de 833
        Compartimento principal = new Compartimento("Principal", 833, new String[]{"simples"});
        
        // "Auxiliar" aceita carga "simples" e "precioso", e tem carga máxima de 222
        Compartimento auxiliar = new Compartimento("Auxiliar", 222, new String[]{"simples", "precioso"});
        
        // "Precioso" aceita apenas carga do tipo "precioso" e tem carga máxima de 21
        Compartimento precioso = new Compartimento("Precioso", 21, new String[]{"precioso"});

        // Adicionando os compartimentos ao avião
        aviaoCargueiro.adicionarCompartimento(principal);
        aviaoCargueiro.adicionarCompartimento(auxiliar);
        aviaoCargueiro.adicionarCompartimento(precioso);

        // INTERAÇÃO COM USUÁRIO
        // Loop principal do sistema que continuará até o usuário escolher sair
        System.out.println("Sistema de Armazenamento Cargueiro");
        while (true) {
            // Mostra o menu de opções para o usuário
            System.out.println("Insira o dígito da ação desejada:");
            System.out.println("(1) Adicionar caixa");
            System.out.println("(2) Solicitar decolagem");
            System.out.println("(3) Sair do sistema");

            // Lê a opção escolhida pelo usuário
            int opcao = entrada.nextInt();

            // Verifica a opção selecionada e executa a ação correspondente
            switch (opcao) {
                case 1:
                    // Chama o método para adicionar uma caixa ao avião
                    adicionarCaixa(entrada, aviaoCargueiro);
                    break;
                case 2:
                    // Autoriza a decolagem do avião (a lógica está na classe Aviao)
                	if (aviaoCargueiro.autorizarDecolagem()) {
                        ManipuladorArquivoJson manipulador = new ManipuladorArquivoJson();
                        manipulador.gravarManifesto(aviaoCargueiro);
                    }
                case 3:
                    // Sai do sistema, fechando o scanner e finalizando o programa
                    System.out.println("Saindo do sistema...");
                    entrada.close(); // Fecha o scanner para evitar vazamentos de recursos
                    System.exit(0);  // Encerra o programa
                    break;
                default:
                    // Mensagem de erro se o usuário selecionar uma opção inválida
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    // Método para adicionar uma nova caixa a um compartimento do avião
    // Recebe o scanner para ler as entradas do usuário e o objeto aviaoCargueiro
    public static void adicionarCaixa(Scanner entrada, Aviao aviaoCargueiro) {
        // Informa ao usuário que ele vai adicionar uma caixa
        System.out.println("Adicionar Caixa:");

        // Solicita o tipo de caixa que o usuário deseja adicionar (simples ou precioso)
        System.out.print("Digite o tipo da caixa: (1) simples ou (2) precioso ");
        int input = entrada.nextInt();

        // Inicializa a variável que irá armazenar o tipo da caixa
        String tipoCaixa = null;

        // Verifica o tipo de caixa escolhido pelo usuário
        switch (input) {
            case 1:
                // Caixa do tipo simples
                tipoCaixa = "simples";
                break;
            case 2:
                // Caixa do tipo precioso
                tipoCaixa = "precioso";
                break;
            default:
                // Se o usuário inserir um valor inválido, exibe uma mensagem de erro e sai do método
                System.out.println("Tipo de caixa inválido. Tente novamente.");
                return; // Interrompe a execução desse método
        }

        // Solicita ao usuário o peso da caixa
        System.out.print("Digite o peso da caixa: ");
        float pesoCaixa = entrada.nextFloat();

        // Cria uma nova caixa com o tipo e peso informados
        Caixa novaCaixa = new Caixa(pesoCaixa, tipoCaixa);

        // Se a caixa for do tipo precioso, tenta armazená-la no compartimento "Precioso"
        if (tipoCaixa.equals("precioso")) {
            boolean armazenar = aviaoCargueiro.adicionarCaixa(aviaoCargueiro.getCompartimento("Precioso"), novaCaixa);
            // Verifica se foi possível armazenar a caixa
            if (armazenar) {
                System.out.println("Caixa armazenada com sucesso no compartimento Precioso.");
            } else {
                System.out.println("Peso no compartimento Precioso excedido.");
            }
        } 
        // Se a caixa for do tipo simples, tenta primeiro armazenar no compartimento "Principal"
        else if (tipoCaixa.equals("simples")) {
            boolean armazenar = aviaoCargueiro.adicionarCaixa(aviaoCargueiro.getCompartimento("Principal"), novaCaixa);
            // Se armazenou com sucesso no Principal
            if (armazenar) {
                System.out.println("Caixa armazenada com sucesso no compartimento Principal.");
            } 
            // Se o compartimento Principal estiver cheio, tenta armazenar no Auxiliar
            else {
                armazenar = aviaoCargueiro.adicionarCaixa(aviaoCargueiro.getCompartimento("Auxiliar"), novaCaixa);
                if (armazenar) {
                    System.out.println("Caixa armazenada com sucesso no compartimento Auxiliar.");
                } else {
                    // Se nenhum compartimento puder armazenar a caixa
                    System.out.println("Peso excedido em ambos os compartimentos.");
                }
            }
        }
    }
}

package cargueiro;

import java.util.ArrayList;
import java.util.List;

public class Aviao {
    // Atributos do avião
    private String nome; // Nome do avião
    private List<Compartimento> compartimentos; // Lista de compartimentos do avião
    private float cargaMinimaDecolagem; // Carga mínima necessária para autorizar a decolagem

    // Construtor do avião
    public Aviao(String nome, float cargaMinimaDecolagem) {
        this.nome = nome; // Define o nome do avião
        this.cargaMinimaDecolagem = cargaMinimaDecolagem; // Define a carga mínima necessária
        this.compartimentos = new ArrayList<>(); // Inicializa a lista de compartimentos
    }

    // Getter para o nome do avião
    public String getNome() {
        return nome;
    }

    // Setter para o nome do avião
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para a carga mínima de decolagem
    public float getCargaMinimaDecolagem() {
        return cargaMinimaDecolagem;
    }

    // Setter para a carga mínima de decolagem
    public void setCargaMinimaDecolagem(float cargaMinimaDecolagem) {
        this.cargaMinimaDecolagem = cargaMinimaDecolagem;
    }

    // Getter para a lista de compartimentos
    public List<Compartimento> getCompartimentos() {
        return compartimentos;
    }

    // Setter para modificar a lista de compartimentos
    public void setCompartimentos(List<Compartimento> compartimentos) {
        this.compartimentos = compartimentos;
    }

    // Método que retorna um compartimento específico com base no nome
    public Compartimento getCompartimento(String nome) {
        // Itera pela lista de compartimentos
        for (Compartimento compartimento : this.compartimentos) {
            // Se o nome do compartimento for o mesmo que o procurado, retorna esse compartimento
            if (compartimento.getNome().equals(nome)) {
                return compartimento;
            }
        }
        // Se não encontrar, retorna null
        return null;
    }

    // Método para autorizar a decolagem com base na carga mínima
    public boolean autorizarDecolagem() {
        float cargaTotal = 0; // Variável para armazenar a carga total

        // Itera por todos os compartimentos e soma a carga atual
        for (Compartimento compartimento : compartimentos) {
            cargaTotal += compartimento.getCargaAtual();
        }

        // Verifica se a carga total é suficiente para decolar
        if (cargaTotal >= this.cargaMinimaDecolagem) {
            System.out.print("DECOLAGEM AUTORIZADA! BOA VIAGEM");
            return true; // Decolagem autorizada
        } else {
            // Caso contrário, exibe uma mensagem informando que a carga mínima não foi atingida
            System.out.println("A carga mínima para decolagem de " + this.cargaMinimaDecolagem + " não foi atingida.");
            System.out.println("Carga atual = " + cargaTotal);
            return false; // Decolagem não autorizada
        }
    }

    // Método para adicionar um compartimento ao avião
    public void adicionarCompartimento(Compartimento compartimento) {
        this.compartimentos.add(compartimento);  // Adiciona o compartimento à lista de compartimentos do avião
    }

    // Método para adicionar uma caixa a um compartimento específico
    public boolean adicionarCaixa(Compartimento compartimento, Caixa caixa) {
        // Verifica se o compartimento faz parte da lista de compartimentos do avião
        if (this.compartimentos.contains(compartimento)) {
            // Se estiver na lista, tenta adicionar a caixa ao compartimento
            return compartimento.adicionarCaixa(caixa);
        } else {
            // Caso contrário, exibe uma mensagem de erro
            System.out.println("Compartimento não encontrado no avião.");
            return false; // Retorna false se o compartimento não existir
        }
    }
}

package cargueiro;

import java.util.ArrayList;
import java.util.List;

public class Compartimento {
    // Atributos do Compartimento
    private String nome; // Nome do compartimento (ex.: Principal, Auxiliar, Precioso)
    private float cargaMaxima; // Carga máxima suportada pelo compartimento
    private float cargaAtual; // Carga atual presente no compartimento
    private String[] tipoPermitidoBagagem; // Tipos de bagagem permitidos (ex.: "simples", "precioso")
    private List<Caixa> caixas; // Lista de caixas armazenadas no compartimento

    // Construtor do Compartimento
    // Recebe nome, carga máxima e tipos permitidos de bagagem, e inicializa a carga atual com 0
    public Compartimento(String nome, float cargaMaxima, String[] tipoPermitidoBagagem) {
        this.nome = nome; // Define o nome do compartimento
        this.cargaAtual = 0; // Inicializa a carga atual com 0
        this.cargaMaxima = cargaMaxima; // Define a carga máxima permitida
        this.tipoPermitidoBagagem = tipoPermitidoBagagem; // Define os tipos de bagagem permitidos
        this.caixas = new ArrayList<>(); // Inicializa a lista de caixas como vazia
    }

    // Getter para o nome do compartimento
    public String getNome() {
        return nome;
    }

    // Setter para o nome do compartimento
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para a carga máxima
    public float getCargaMaxima() {
        return cargaMaxima;
    }

    // Setter para a carga máxima
    public void setCargaMaxima(float cargaMaxima) {
        this.cargaMaxima = cargaMaxima;
    }

    // Getter para a carga atual
    public float getCargaAtual() {
        return cargaAtual;
    }

    // Setter para a carga atual
    public void setCargaAtual(float cargaAtual) {
        this.cargaAtual = cargaAtual;
    }

    // Getter para os tipos permitidos de bagagem
    public String[] getTipoPermitidoBagagem() {
        return tipoPermitidoBagagem;
    }

    // Setter para os tipos permitidos de bagagem
    public void setTipoPermitidoBagagem(String[] tipoPermitidoBagagem) {
        this.tipoPermitidoBagagem = tipoPermitidoBagagem;
    }

    // Método para verificar se o peso de uma caixa pode ser acomodado no compartimento
    // Retorna true se o peso total após adicionar a caixa for menor ou igual à carga máxima
    public boolean checarDisponibilidade(float peso) {
        if ((this.cargaAtual + peso) <= this.cargaMaxima) {
            return true; // Há espaço suficiente no compartimento
        } else {
            return false; // Espaço insuficiente
        }
    }

    // Método para adicionar uma caixa ao compartimento
    // Verifica se o tipo da caixa é permitido e se há espaço suficiente para armazená-la
    public boolean adicionarCaixa(Caixa caixa) {
        boolean tipoPermitido = false; // Variável para verificar se o tipo da caixa é permitido

        // Verifica se o tipo da caixa corresponde a um dos tipos permitidos no compartimento
        for (String tipo : this.tipoPermitidoBagagem) {
            if (tipo.equals(caixa.getTipo())) {
                tipoPermitido = true;
                break; // Se encontrar o tipo, encerra o loop
            }
        }

        // Checa se o compartimento tem espaço suficiente e se o tipo da caixa é permitido
        if (checarDisponibilidade(caixa.getPeso()) && tipoPermitido) {
            this.caixas.add(caixa); // Adiciona a caixa à lista de caixas no compartimento
            this.cargaAtual += caixa.getPeso(); // Atualiza a carga atual com o peso da nova caixa
            return true; // A caixa foi armazenada com sucesso
        }
        return false; // Não foi possível armazenar a caixa
    }
}

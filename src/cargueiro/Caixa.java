package cargueiro;

public class Caixa {
    private int id; // Identificador único da caixa
    private float peso; // Peso da caixa
    private String tipo; // Tipo da caixa (por exemplo, "simples" ou "precioso")

    // Construtor que inicializa o peso e o tipo da caixa
    public Caixa(float peso, String tipo) {
        this.peso = peso; // Define o peso da caixa
        this.tipo = tipo; // Define o tipo da caixa
    }

    // Getter para o ID da caixa
    public int getId() {
        return id;
    }

    // Setter para o ID da caixa
    public void setId(int id) {
        this.id = id; // Define o ID da caixa
    }

    // Getter para o peso da caixa
    public float getPeso() {
        return peso;
    }

    // Setter para o peso da caixa
    public void setPeso(float peso) {
        this.peso = peso; // Define o peso da caixa
    }

    // Getter para o tipo da caixa
    public String getTipo() {
        return tipo;
    }

    // Setter para o tipo da caixa
    public void setTipo(String tipo) {
        this.tipo = tipo; // Define o tipo da caixa
    }
}

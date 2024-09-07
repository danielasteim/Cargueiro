package cargueiro;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

public class ManipuladorArquivoJson {
    private Gson gson;

    public ManipuladorArquivoJson() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Método para gravar o manifesto da carga em um arquivo JSON
    public void gravarManifesto(Aviao aviao) {
        // Cria um mapa para armazenar os dados do manifesto
        Map<String, Object> manifesto = new LinkedHashMap<>();

        // Adiciona cada compartimento ao manifesto
        for (Compartimento compartimento : aviao.getCompartimentos()) {
            manifesto.put(compartimento.getNome(), compartimento.toJson());
        }

        // Grava o manifesto em um arquivo JSON
        try (FileWriter writer = new FileWriter("manifesto_carga.json")) {
            gson.toJson(manifesto, writer);
            System.out.println("Manifesto da carga gravado com sucesso.");
        } catch (JsonIOException | IOException e) {
            System.out.println("Erro ao gravar arquivo: " + e.getMessage());
        }
    }
}

package ao.com.angotech.model;

import org.bouncycastle.crypto.signers.ISOTrailers;

import java.util.List;

public class UserInput {

    private String nomeCompleto;
    private String areaInteresse;
    private List<String> experiencias;
    private String formacao;
    private List<String> habilidades;
    private String cidade;

    public UserInput(String nomeCompleto, String areaInteresse, List<String> experiencias, String formacao, List<String> habilidades, String cidade) {
        this.nomeCompleto = nomeCompleto;
        this.areaInteresse = areaInteresse;
        this.experiencias = experiencias;
        this.formacao = formacao;
        this.habilidades = habilidades;
        this.cidade = cidade;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getAreaInteresse() {
        return areaInteresse;
    }

    public List<String> getExperiencias() {
        return experiencias;
    }

    public String getFormacao() {
        return formacao;
    }

    public List<String> getHabilidades() {
        return habilidades;
    }

    public String getCidade() {
        return cidade;
    }
}

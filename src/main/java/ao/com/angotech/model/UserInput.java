package ao.com.angotech.model;

import java.util.List;

public class UserInput {

    private String nomeCompleto;
    private String areaInteresse;
    private List<String> experiencias;
    private String formacao;
    private List<String> habilidades;

    public UserInput(String nomeCompleto, String areaInteresse, List<String> experiencias, String formacao, List<String> habilidades) {
        this.nomeCompleto = nomeCompleto;
        this.areaInteresse = areaInteresse;
        this.experiencias = experiencias;
        this.formacao = formacao;
        this.habilidades = habilidades;
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
}

package model;

public class cliente {
    String cpf;
    String nome;
    String rua;
    int nr_rua;
    String dt_nasc;
    Double renda;
    
    public cliente(){
        cpf = "";
        nome = "";
        rua = "";
        nr_rua = 0;
        dt_nasc = "";
        renda = 0.00;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNr_rua() {
        return nr_rua;
    }

    public void setNr_rua(int nr_rua) {
        this.nr_rua = nr_rua;
    }

    public String getDt_nasc() {
        return dt_nasc;
    }

    public void setDt_nasc(String dt_nasc) {
        this.dt_nasc = dt_nasc;
    }

    public Double getRenda() {
        return renda;
    }

    public void setRenda(Double renda) {
        this.renda = renda;
    }
}

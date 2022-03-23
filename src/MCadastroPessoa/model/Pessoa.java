/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MCadastroPessoa.model;


public class Pessoa {
    private int idPessoa;//PK
    private String nomePessoa;
    private String cpf;
    private String endereco;
    private String telefone;
    private int idade;
    private boolean status;//identifica se a pessoa esta ativa ou não
    
    
    
    public Pessoa(){
        //Construtor vazio para usar os métodos acessores geters and seters
    }

    public Pessoa(int idPessoa, String nomePessoa, String cpf, String endereco, String telefone, int idade, boolean status) {
        this.idPessoa = idPessoa;
        this.nomePessoa = nomePessoa;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.idade = idade;
        this.status = status;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "idPessoa=" + idPessoa + ", nomePessoa=" + nomePessoa + ", cpf=" + cpf + ", endereco=" + endereco + ", telefone=" + telefone + ", idade=" + idade + ", status=" + status + '}';
    }
    

    
}

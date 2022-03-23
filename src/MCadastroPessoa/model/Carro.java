/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MCadastroPessoa.model;

public class Carro{
    private int idCarro;//PK
    private String placa;
    private String marca;
    private String modelo;
    private int anoFabricacao;
    private int anoModelo;
    private String cor;
    private int nPortas;
    private int idPessoa;//FK banco de dados

    public Carro() {
    }

    public Carro(int idCarro, String placa, String marca, String modelo, int anoFabricacao, int anoModelo, String cor, int nPortas, int idPessoa) {
        this.idCarro = idCarro;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.anoModelo = anoModelo;
        this.cor = cor;
        this.nPortas = nPortas;
        this.idPessoa = idPessoa;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getnPortas() {
        return nPortas;
    }

    public void setnPortas(int nPortas) {
        this.nPortas = nPortas;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    @Override
    public String toString() {
        return "Carro{" + "idCarro=" + idCarro + ", placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + ", anoFabricacao=" + anoFabricacao + ", anoModelo=" + anoModelo + ", cor=" + cor + ", nPortas=" + nPortas + ", idPessoa=" + idPessoa +'}'+"\n";
    }
    
}

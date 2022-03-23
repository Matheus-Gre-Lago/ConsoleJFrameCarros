/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MCadastroPessoa.controller;


import MCadastroPessoa.model.Carro;

import java.util.ArrayList;

public class CCarro implements InterfaceController<Carro>{
    ArrayList<Carro> carros = new ArrayList<>();
    int idCarro = 1;
    
    @Override
    public int gerarId(){
        return this.idCarro++;
    }

    
    @Override
    public ArrayList<Carro> getAll() {
        return this.carros;
    }

    @Override
    public void add(Carro c){
        this.carros.add(c);
    }
    
    @Override
    public boolean deletar(Carro c){
        boolean rc = this.carros.remove(c);
        return rc;
    }
    
    public void mokCarro(){
        Carro c1 = new Carro();
        c1.setIdCarro(this.gerarId());
        c1.setPlaca("CHS6647");
        c1.setMarca("Peugeot");
        c1.setModelo("405 GRI");
        c1.setAnoFabricacao(1995);
        c1.setAnoModelo(1996);
        c1.setCor("Verde");
        c1.setnPortas(5);
        c1.setIdPessoa(1);
        this.add(c1);
        
        Carro c2 = new Carro();
        c2.setIdCarro(this.gerarId());
        c2.setPlaca("IPP1234");
        c2.setMarca("GM");
        c2.setModelo("Corsa");
        c2.setAnoFabricacao(1996);
        c2.setAnoModelo(1996);
        c2.setCor("Cinza");
        c2.setnPortas(3);
        c2.setIdPessoa(2);
        this.add(c2);
    }
    
    public boolean verPlaca(String placa){
        boolean verPlaca = false;
        for (Carro listCar: carros){
            if(listCar.getPlaca().equalsIgnoreCase(placa)){
                verPlaca = true;
                break;
            }
        }
        return verPlaca;
    }
    
    public boolean verAnoMod(int anoFab,int anoMod){
        boolean teste = false;
        if(anoMod == anoFab || anoMod == anoFab+1){
            teste = true;
        }
        return teste;
    }
    
    @Override
    public Carro getByDoc(String placa){
        Carro car = null;
        for(Carro listCar: carros){
            if(listCar.getPlaca().equalsIgnoreCase(placa)){
                car = listCar;
                break;
            }
        }
        return car;
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MCadastroPessoa.controller;

import MCadastroPessoa.model.VagaEstacionamento;

import java.util.ArrayList;


public class CVagaEstacionamento implements InterfaceController<VagaEstacionamento>{

    ArrayList<VagaEstacionamento> vagas = new ArrayList<>();
    int idVaga = 1;
    
    @Override
    public void add(VagaEstacionamento o) {
        vagas.add(o);
    }

    @Override
    public ArrayList<VagaEstacionamento> getAll() {
        return vagas;
    }

    @Override
    public boolean deletar(VagaEstacionamento o) {
        boolean del = vagas.remove(o);
        return  del;
    }

    @Override
    public VagaEstacionamento getByDoc(String d) {
       return null;
    }

    @Override
    public int gerarId() {
        return idVaga++;
    }
    
    public void mokVagas(){
        VagaEstacionamento v1 = new VagaEstacionamento();
        v1.setIdVaga(gerarId());
        v1.setTpVaga("Carro");
        //v1.setC(MCadastroPessoa.cadCarros.getByDoc("CHS6647"));
        vagas.add(v1);
        
        VagaEstacionamento v2 = new VagaEstacionamento();
        v2.setIdVaga(gerarId());
        v2.setTpVaga("Carro");
        //v2.setC(MCadastroPessoa.cadCarros.getByDoc("IPP1234"));
        vagas.add(v2);
    }
    
}

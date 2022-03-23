/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MCadastroPessoa.controller;

import java.util.ArrayList;

/**
 *
 * @author jairb
 */
public interface InterfaceController<T> {
    
    public int gerarId();
    
    public void add(T o);
    
    public ArrayList<T> getAll();
    
    public boolean deletar(T o);
    
    public T getByDoc(String d);
    
}

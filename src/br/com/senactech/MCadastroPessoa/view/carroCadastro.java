package br.com.senactech.MCadastroPessoa.view;

import MCadastroPessoa.model.Carro;
import MCadastroPessoa.model.Pessoa;
import utils.ValidaCPF;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static MCadastroPessoa.MCadastroPessoa.cadCarros;
import static MCadastroPessoa.MCadastroPessoa.cadPessoas;


public class carroCadastro extends  JFrame{
    private JTextField txtAnoModelo;
    private JTable tableCarros;
    private JTextField txtPlacaVeiculo;
    private JTextField txtAnoFabricacao;
    private JTextField txtPortas;
    private JTextField txtCor;
    private JButton buttonLimpar;
    private JButton buttonSalvar;
    private JButton buttonPesquisar;
    private JButton buttonDeletar;
    private JButton buttonConfirmar;
    private JButton buttonEditar;
    private JComboBox comboBoxMarca;
    private JTextField txtCpfProprietario;
    private JPanel Painel;
    private JTextField txtModelo;
    private JTextField txtNomeProprietario;

    public static void main(String[] args) {
        JFrame cadastroCarros = new JFrame("carroCadastro");
        cadastroCarros.setContentPane(new carroCadastro().Painel);
        cadastroCarros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cadastroCarros.pack();
        cadastroCarros.setVisible(true);
    }


    public carroCadastro() {
        cadastroMokCarros();

        txtPlacaVeiculo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String placa = txtPlacaVeiculo.getText();
                txtPlacaVeiculo.setText(placa.toUpperCase());
            }
        });

        txtAnoFabricacao.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String caracteres = "0987654321";
                if (!caracteres.contains(e.getKeyChar() + "")) {
                    e.consume();
                }
            }
        });

        txtAnoModelo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                String caracteres = "0987654321";
                if (!caracteres.contains(e.getKeyChar() + "")) {
                    e.consume();
                }
            }
        });

        txtPortas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                String caracteres = "0987654321";
                if (!caracteres.contains(e.getKeyChar() + "")) {
                    e.consume();
                }
            }
        });

        buttonLimpar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });

        buttonLimpar.addActionListener(e -> {
            txtAnoModelo.setText("");
            txtAnoModelo.setText("");
            txtAnoFabricacao.setText("");
            txtCor.setText("");
            txtPortas.setText("");
            txtCpfProprietario.setText("");
            comboBoxMarca.setSelectedIndex(0);

            txtPlacaVeiculo.requestFocus();
        });


        txtCpfProprietario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });

        txtCpfProprietario.addActionListener(e->{
            if(!ValidaCPF.isCPF(txtCpfProprietario.getText())){
                JOptionPane.showMessageDialog(this,
                        "CPF informado está errado !",
                        ".: Erro :.", JOptionPane.ERROR_MESSAGE);
                txtCpfProprietario.requestFocus();
            }else if(cadPessoas.verCPF(txtCpfProprietario.getText())){
                int id = cadPessoas.pesqIdPes(txtCpfProprietario.getText());
                txtCpfProprietario.setText(cadPessoas.getNomePes(id));
            }
        });

        buttonSalvar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });

        buttonSalvar.addActionListener(e -> {
            if(validaInputs()){
                int id = cadCarros.gerarId();
                int idPESSOA = cadPessoas.pesqIdPes(txtCpfProprietario.getText());
                String placa = txtPlacaVeiculo.getText();
                String marca = comboBoxMarca.getSelectedItem().toString();
                int anoFabricacao = Integer.parseInt(txtAnoFabricacao.getText());
                int anoModelo = Integer.parseInt(txtAnoModelo.getText());
                String cor = txtCor.getText();
                int portas = Integer.parseInt(txtPortas.getText());
                String modelo = txtModelo.getText();
                String nome = txtNomeProprietario.getText();
                String cpf = txtCpfProprietario.getText();

                Pessoa pessoa = new Pessoa(idPESSOA , nome, cpf , null , null , 0 , true);
                cadPessoas.add(pessoa);

                Carro carro = new Carro(id , placa , marca , modelo , anoFabricacao , anoModelo , cor , portas , idPESSOA);
                cadCarros.add(carro);

                JOptionPane.showMessageDialog(null , "Salvo com Sucessso");
                buttonLimpar.doClick();

                txtNomeProprietario.setText(cadPessoas.getNomePes(carro.getIdPessoa()));
                String[] informacoes = new String[4];

                for (Carro c : cadCarros.getAll()) {
                    informacoes[0] = c.getPlaca();
                    informacoes[1] = c.getMarca();
                    informacoes[2]= c.getModelo();
                    informacoes[3] = cadPessoas.getNomePes(c.getIdPessoa());


                    System.out.println();
                    System.out.println(informacoes[0]);
                    System.out.println(informacoes[1]);
                    System.out.println(informacoes[2]);
                    System.out.println(informacoes[3]);
                    System.out.println();
                }
                addRowToTable();
            }
        });

        tableCarros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonDeletar.setEnabled(true);
                buttonEditar.setEnabled(true);
            }
        });

        tableCarros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });

        buttonDeletar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });

        buttonDeletar.addActionListener(e ->  {
            buttonEditar.setEnabled(false);
            int linha;
            String placa;
            linha = tableCarros.getSelectedRow();
            placa = (String) tableCarros.getValueAt(linha , 0);

            Carro carro = cadCarros.getByDoc(placa);

            Object[] resp = {"sim","não"};

            int resposta = JOptionPane.showOptionDialog(null, "Deseja deletar " + carro.getPlaca() + "?",
                    ".: Deletar :." , JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,null,resp,resp[0]);

            if(resposta == 0){
                cadCarros.deletar(carro);
                addRowToTable();
                JOptionPane.showMessageDialog(null,"Carro deletado");
            }else{
                JOptionPane.showMessageDialog(null,"Entendemos sua decisão",
                        ".: Deletar :.", JOptionPane.INFORMATION_MESSAGE);
            }
            buttonLimpar.doClick();
        });

        buttonEditar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });

        buttonEditar.addActionListener(e -> {
            buttonEditar.setEnabled(false);
            buttonDeletar.setEnabled(false);
            buttonEditar.setEnabled(false);
            txtCpfProprietario.setEnabled(false);
            buttonEditar.setEnabled(false);
            buttonLimpar.setText("Cancelar");

            int linha = tableCarros.getSelectedRow();
            String placa = (String) tableCarros.getValueAt(linha , 0);
            Carro carro = cadCarros.getByDoc(placa);

            txtPlacaVeiculo.setText(carro.getPlaca());
            txtModelo.setText(carro.getModelo());
            txtAnoFabricacao.setText(Integer.toString(carro.getAnoFabricacao()));
            txtAnoModelo.setText(Integer.toString(carro.getAnoModelo()));
            txtPortas.setText(Integer.toString(carro.getnPortas()));
            txtCpfProprietario.setText(cadPessoas.getCpfPes(carro.getIdPessoa()));
            comboBoxMarca.setSelectedItem(carro.getMarca());
            txtNomeProprietario.setText(cadPessoas.getNomePes(carro.getIdPessoa()));
        });

        buttonConfirmar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });

        buttonConfirmar.addActionListener(e->{
            Carro carro = cadCarros.getByDoc(txtPlacaVeiculo.getText());
            Pessoa pessoa = cadPessoas.getByDoc(txtCpfProprietario.getText());

            carro.setAnoFabricacao(Integer.parseInt(txtAnoFabricacao.getText()));
            carro.setAnoModelo(Integer.parseInt(txtAnoModelo.getText()));
            carro.setCor(txtCor.getText());
            carro.setMarca(comboBoxMarca.getSelectedItem().toString());
            carro.setModelo(txtModelo.getText());
            carro.setnPortas(Integer.parseInt(txtPortas.getText()));
            carro.setIdPessoa(cadPessoas.pesqIdPes(txtCpfProprietario.getText()));
            pessoa.setNomePessoa(txtNomeProprietario.getText());


            addRowToTable();

            buttonLimpar.doClick();
            buttonLimpar.setText("Limpar");

            JOptionPane.showMessageDialog(null , "Informações atualizadas");

            addRowToTable();
        });

        buttonPesquisar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });

        buttonPesquisar.addActionListener(e->{
            if(!txtCpfProprietario.getText().equals("")){
                String text = txtCpfProprietario.getText();
                String[] colunaNome = {"Placa", "Marca", "Modelo", "Proprietario"};

                String[][] informacoes = new String[10][5];
                int i =0;


                for (Carro c : cadCarros.getAll()) {
                    if(cadPessoas.getIdPorCpf(text) == c.getIdPessoa()) {
                        informacoes[i][0] = c.getPlaca();
                        informacoes[i][1] = c.getMarca();
                        informacoes[i][2] = c.getModelo();
                        informacoes[i][3] = cadPessoas.getNomePes(c.getIdPessoa());

                        i++;
                        System.out.println("achei um");
                    }else {
                        System.out.println("nao foi");
                    }
                    tableCarros.setModel(new DefaultTableModel(informacoes , colunaNome));
                }
            }else{
                String menssagem = "Para pesquisar CPF o campo nao pode estar vazio";
                JOptionPane.showMessageDialog(this, menssagem, ".: Erro :.",
                        JOptionPane.ERROR_MESSAGE);
                txtCpfProprietario.requestFocus();
            }
        });
    }

    public void cadastroMokCarros(){
        cadPessoas.mokPessoas();
        cadCarros.mokCarro();
        addRowToTable();
    }

    public void addRowToTable() {
        String[] colunaNome = {"Placa", "Marca", "Modelo", "Proprietario"};
        int i =0;
        String[][] informacoes = new String[10][5];

        for (Carro c : cadCarros.getAll()) {
            informacoes[i][0] = c.getPlaca();
            informacoes[i][1] = c.getMarca();
            informacoes[i][2]= c.getModelo();
            informacoes[i][3] = cadPessoas.getNomePes(c.getIdPessoa());

            i++;
        }
        tableCarros.setModel(new DefaultTableModel(informacoes , colunaNome));
    }


    private boolean validaInputs(){
        Boolean verPlaca;
        String placa = txtPlacaVeiculo.getText().toUpperCase();
        verPlaca = (placa.length() == 7 && !cadCarros.verPlaca(placa));

        if (!verPlaca) {
            String menssagem = "Placa já cadastrada ou incorreta!";
            JOptionPane.showMessageDialog(this, menssagem, ".: Erro :.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(comboBoxMarca.getSelectedItem().equals("Selecione...")){
            String menssagem = "Marca vazia";
            JOptionPane.showMessageDialog(this, menssagem, ".: Erro :.",
                    JOptionPane.ERROR_MESSAGE);
            comboBoxMarca.requestFocus();
            return false;
        }

        if(txtAnoModelo.getText().equals("") || txtAnoFabricacao.getText().equals("")){
            String menssagem = "Data vazia";
            JOptionPane.showMessageDialog(this, menssagem, ".: Erro :.",
                    JOptionPane.ERROR_MESSAGE);
            txtAnoModelo.requestFocus();
            return false;
        }

        Calendar calendario = GregorianCalendar.getInstance();
        int anoAtual = calendario.get(calendario.YEAR);
        int anoFabricacao = Integer.parseInt(txtAnoFabricacao.getText());

        if(anoFabricacao > anoAtual){
            String menssagem = "Ano de fabricação maior que ano atual";
            JOptionPane.showMessageDialog(this, menssagem, ".: Erro :.",
                    JOptionPane.ERROR_MESSAGE);
            txtAnoFabricacao.requestFocus();
            return false;
        }

        boolean testaAnomodelo;
        int anoModelo = Integer.parseInt(txtAnoModelo.getText());
        testaAnomodelo = cadCarros.verAnoMod(anoFabricacao , anoModelo);

        if(!testaAnomodelo){
            String menssagem = "ano modelo invalido";
            JOptionPane.showMessageDialog(this, menssagem, ".: Erro :.",
                    JOptionPane.ERROR_MESSAGE);
            txtAnoModelo.requestFocus();
            return false;
        }

        if(txtCor.getText().equals("")){
            String menssagem = "Campo Cor Vazio";
            JOptionPane.showMessageDialog(this, menssagem, ".: Erro :.",
                    JOptionPane.ERROR_MESSAGE);
            txtCor.requestFocus();
            return false;
        }

        if (txtPortas.getText().equals("")) {
            String menssagem = "Campo porta Vazio";
            JOptionPane.showMessageDialog(this, menssagem, ".: Erro :.",
                    JOptionPane.ERROR_MESSAGE);
            txtPortas.requestFocus();
            return false;
        }

        if (txtModelo.getText().equals("")) {
            String menssagem = "Campo modelo Vazio";
            JOptionPane.showMessageDialog(this, menssagem, ".: Erro :.",
                    JOptionPane.ERROR_MESSAGE);
            txtModelo.requestFocus();
            return false;
        }

        if (txtNomeProprietario.getText().equals("")) {
            String menssagem = "Campo nome Vazio";
            JOptionPane.showMessageDialog(this, menssagem, ".: Erro :.",
                    JOptionPane.ERROR_MESSAGE);
            txtNomeProprietario.requestFocus();
            return false;
        }

        if (txtCpfProprietario.getText().equals("")) {
            String menssagem = "Campo cpf proprietario Vazio";
            JOptionPane.showMessageDialog(this, menssagem, ".: Erro :.",
                    JOptionPane.ERROR_MESSAGE);
            txtCpfProprietario.requestFocus();
            return false;
        }
        return true;
    }
}

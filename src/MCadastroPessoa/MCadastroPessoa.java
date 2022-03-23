/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MCadastroPessoa;


import MCadastroPessoa.controller.CCarro;
import MCadastroPessoa.controller.CPessoa;
import MCadastroPessoa.controller.CVagaEstacionamento;
import MCadastroPessoa.model.Carro;
import MCadastroPessoa.model.Pessoa;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class MCadastroPessoa {

    public static Scanner ler = new Scanner(System.in);
    public static CPessoa cadPessoas = new CPessoa();
    public static CCarro cadCarros = new CCarro();
    public static CVagaEstacionamento cadVagas = new CVagaEstacionamento();
    


    public static String leTexto() {
        Scanner teclado = new Scanner(System.in);
        return teclado.nextLine();
    }

    public static void menu() {
        System.out.println("\n-- Menu Principal --");
        System.out.println("1 - Ir para Pessoa");
        System.out.println("2 - Ir para Veículo");
        System.out.println("0 - Sair");
        System.out.print("Escolha um opção: ");
    }

    public static void menuP() {
        System.out.println("\n-- Cadastro Pessoa --");
        System.out.println("1 - Cadastrar Pessoa");
        System.out.println("2 - Alterar Status Pessoa");
        System.out.println("3 - Atualizar Pessoa");
        System.out.println("4 - Deletar Pessoa");
        System.out.println("5 - Imprimir Pessoas");
        System.out.println("0 - Voltar");
        System.out.print("Escolha um opção: ");
    }

    public static void menuC() {
        System.out.println("\n-- Cadastro Carro --");
        System.out.println("1 - Cadastrar Carro");
        System.out.println("2 - Atualizar Carro");
        System.out.println("3 - Deletar Carro");
        System.out.println("4 - Imprimir Carros");
        System.out.println("0 - Voltar");
        System.out.print("Escolha um opção: ");
    }

    public static void cadPessoa() {
        boolean system;

        do {
            System.out.println("-- Cadastro de Pessoa --");
            System.out.println("");
            int id;
            String nomePessoa;
            String cpf;
            String endereco;
            String telefone;
            int idade;
            boolean status;
            System.out.print("Informe o nome da Pessoa: ");
            nomePessoa = leTexto();
            boolean testaCPF = false;
            do {
                System.out.print("Informe o CPF: ");
                cpf = leTexto();
                if (cpf.length() != 11) {
                    testaCPF = true;
                    System.out.println("CPF incorreto, tente novamente!");
                } else {
                    testaCPF = cadPessoas.verCPF(cpf);
                }
            } while (testaCPF);
            System.out.print("Informe o endereço: ");
            endereco = leTexto();
            boolean testaTel;
            do {
                testaTel = false;
                System.out.print("Informe o telefone com DDD: ");
                telefone = leTexto();
                if (telefone.length() != 10 && telefone.length() != 11) {
                    testaTel = true;
                    System.out.println("Telefone informado esta incorreto");
                }
            } while (testaTel);
            System.out.print("Informe a idade: ");
            idade = ler.nextInt();
            System.out.print("Pessoa esta ativa no cadastro? 1-Sim / 2-Não: ");
            byte statusN = ler.nextByte();
            status = statusN == 1;
            id = cadPessoas.gerarId();
            Pessoa p = new Pessoa(id, nomePessoa, cpf, endereco, telefone, idade, status);
            cadPessoas.add(p);

            System.out.print("Deseja continuar cadastrando? S/N: ");
            char continuar = ler.next().charAt(0);
            system = (continuar == 's' || continuar == 'S');
        } while (system);
    }

    public static void alteraStatus() {
        System.out.println("-- Altera Status --");
        System.out.println("");
        System.out.print("Informe o CPF: ");
        String cpf = leTexto();
        if (cadPessoas.verCPF(cpf)) {
            int status;
            if (cadPessoas.verStatusPes(cpf)) {
                System.out.println("Status ativo, quer inativar esta Pessoa: 0-Sim | 1-Não");
                status = ler.nextInt();
            } else {
                System.out.println("Status inativo, quer ativar esta Pessao: 1-Sim | 0-Não");
                status = ler.nextInt();
            }
            cadPessoas.alteraStatus(cpf, status);
        } else {
            System.out.println("CPF não existe!");
            System.out.println("Deseja cadastrar este CPF? 1-Sim | 0-Não");
            int c = ler.nextInt();
            if (c == 1) {
                cadPessoa();
            }
        }
    }

    public static void atualizaPessoa() {
        System.out.println("-- Atualiza Pessoa --");
        System.out.println("");
        System.out.print("Informe o CPF: ");
        String cpf = leTexto();
        if (cadPessoas.verCPF(cpf)) {
            Pessoa p = new Pessoa();
            p = cadPessoas.getByDoc(cpf);
            System.out.println("Atualizando Pessoa: " + p.getNomePessoa());
            boolean atualiza = false;
            do {
                System.out.println("1 - Atualizar tudo");
                System.out.println("2 - Atualizar nome");
                System.out.println("3 - Atualizar endereço");
                System.out.println("4 - Atualizar telefone");
                System.out.println("5 - Atualizar idade");
                System.out.println("Escolha um opção:");
                int op = ler.nextInt();
                switch (op) {
                    case 1:
                        System.out.print("Informe o nome: ");
                        p.setNomePessoa(leTexto());
                        System.out.print("\nInforme o endereço: ");
                        p.setEndereco(leTexto());
                        System.out.print("\nInforme o telefone: ");
                        p.setTelefone(leTexto());
                        System.out.print("\nInforme a idade: ");
                        p.setIdade(ler.nextInt());
                        atualiza = false;
                        break;
                    case 2:
                        System.out.print("Informe o nome: ");
                        p.setNomePessoa(leTexto());
                        atualiza = false;
                        break;
                    case 3:
                        System.out.print("\nInforme o endereço: ");
                        p.setEndereco(leTexto());
                        atualiza = false;
                        break;
                    case 4:
                        System.out.print("\nInforme o telefone: ");
                        p.setTelefone(leTexto());
                        atualiza = false;
                        break;
                    case 5:
                        System.out.print("\nInforme a idade: ");
                        p.setIdade(ler.nextInt());
                        atualiza = false;
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente!");
                        atualiza = true;
                }
            } while (atualiza);
            System.out.println("Atualização concluída com sucesso!");
        } else {
            System.out.println("CPF não existe!");
            System.out.println("Deseja cadastrar este CPF? 1-Sim | 0-Não");
            int c = ler.nextInt();
            if (c == 1) {
                cadPessoa();
            }
        }
    }

    public static void imprimePessoas() {
        if (!cadPessoas.getAll().isEmpty()) {
            for (Pessoa listPes : cadPessoas.getAll()) {
                System.out.println("---");
                System.out.println("Id: " + listPes.getIdPessoa());
                System.out.println("Nome: " + listPes.getNomePessoa());
                System.out.println("CPF: " + listPes.getCpf());
                System.out.print("Status: ");
                if (listPes.isStatus()) {
                    System.out.println("Ativo");
                } else {
                    System.out.println("Inativo");
                }
            }
        } else {
            System.out.println("Não existem pessoas cadastradas!");
        }
    }

    public static void deletarPessoa() {
        System.out.println("-- Deletar Pessoa --");
        System.out.println("");
        boolean delP;
        do {
            delP = false;
            System.out.print("Informe o CPF: ");
            String cpf = leTexto();
            if (cadPessoas.verCPF(cpf)) {
                Pessoa p;
                p = cadPessoas.getByDoc(cpf);
                System.out.println("Deseja realmente deletar esta pessoa? "
                        + p.getNomePessoa() + " 1-Sim | 0-Não");
                int i = ler.nextInt();
                if (i == 1) {
                    if (cadPessoas.deletar(p)) {
                        System.out.println("Pessoa deletada com sucesso!");
                    } else {
                        System.out.println("Ocorreu um erro ao deletar!");
                    }
                } else {
                    System.out.println("Ok, entendemos sua decisão!");
                }
            } else {
                System.out.println("CPF inválido!");
                System.out.print("Deseja tentar novamente? 1-Sim | 0-Não");
                int i = ler.nextInt();
                delP = i == 1;
            }
        } while (delP);
    }

    public static void cadCarro() {
        boolean continuarCar;
        do {
            System.out.println("-- Cadastro de Veículo --");
            Carro c = new Carro();
            boolean verPlaca;
            String placa;
            do {
                System.out.print("Informe a placa (ABC1D23):");
                placa = leTexto().toUpperCase();
                verPlaca = (placa.length() == 7 && !cadCarros.verPlaca(placa));
                if (!verPlaca) {
                    System.out.println("Placa já cadastrada ou incorreta!");
                }
            } while (!verPlaca);
            c.setPlaca(placa);
            System.out.print("Informe a marca: ");
            c.setMarca(leTexto().toUpperCase());
            System.out.print("Informe o modelo: ");
            c.setModelo(leTexto().toUpperCase());
            //Verfica e coleta anoFabricação
            Calendar cal = GregorianCalendar.getInstance();
            int anoAtual = cal.get(Calendar.YEAR);
            do {
                System.out.print("Informe ano fabricação: ");
                c.setAnoFabricacao(ler.nextInt());
                if (c.getAnoFabricacao() > anoAtual) {
                    System.out.println("Ano fabricação inválido, tente novamente!");
                }
            } while (c.getAnoFabricacao() > anoAtual);
            //Verfica e coleta anoModelo
            boolean testaAnoMod;
            do {
                System.out.print("Informe ano modelo: ");
                int anoMod = ler.nextInt();
                testaAnoMod = cadCarros.verAnoMod(c.getAnoFabricacao(), anoMod);
                if (testaAnoMod) {
                    c.setAnoModelo(anoMod);
                } else {
                    System.out.println("Ano modelo inválido, tente novamente!");
                }
            } while (!testaAnoMod);
            System.out.print("Informe a cor: ");
            c.setCor(leTexto().toUpperCase());
            System.out.print("Informe num. de portas: ");
            c.setnPortas(ler.nextInt());
            boolean cpfExiste = false;
            do {
                System.out.print("Informe CPF do proprietário: ");
                String cpf = leTexto();
                cpfExiste = cadPessoas.verCPF(cpf);
                if (cpfExiste) {
                    c.setIdPessoa(cadPessoas.pesqIdPes(cpf));
                } else {
                    System.out.println("CPF inválido, tente novamente!");
                }
            } while (!cpfExiste);
            cadCarros.add(c);
            System.out.println("Cadastrado com sucesso!"
                    + "\nCarro sob a placa " + c.getPlaca()
                    + " e atribuido ao proprietário(a) "
                    + cadPessoas.getNomePes(c.getIdPessoa()));

            System.out.println("Deseja cadastrar outro carro? 1-Sim | 0-Não");
            int cCar = ler.nextInt();
            continuarCar = cCar == 1;
        } while (continuarCar);
    }

    public static void imprimirCarros() {
        System.out.println("-- Lista de Carros --");
        for (Carro listCar : cadCarros.getAll()) {
            System.out.println("---");
            System.out.println("Placa: " + listCar.getPlaca());
            System.out.println("Marca: " + listCar.getMarca());
            System.out.println("Modelo: " + listCar.getModelo());
            System.out.println("Proprietário: "
                    + cadPessoas.getNomePes(listCar.getIdPessoa()));
        }
    }

    public static void deletarCarro() {
        System.out.println("-- Remover Veículo --");
        System.out.print("Informe a placa do veículo: ");
        String placaTemp = leTexto();
        if (cadCarros.verPlaca(placaTemp)) {
            Carro car = cadCarros.getByDoc(placaTemp);
            System.out.print("Deseja realmente deletar este carro?"
                    + "\nProprietário: " + cadPessoas.getNomePes(car.getIdPessoa())
                    + "\nPlaca: " + car.getPlaca()
                    + "\nMarca: " + car.getMarca()
                    + "\nModelo: " + car.getModelo()
                    + "\nCor: " + car.getCor()
                    + "\nDigite 1 para sim, 0 para não: ");
            int op = ler.nextInt();
            if (op == 1) {
                cadCarros.deletar(car);
                System.out.println("Carro removido com sucesso!");
            } else {
                System.out.println("Operação cancelada.");
            }
        } else {
            System.out.println("Placa incorreta ou carro não cadastrado!!!");
        }
    }

    public static void alterarCarro() {
        System.out.println("-- Atualizar Veículo --");
        System.out.print("Informe a placa do veículo: ");
        String placa = leTexto().toUpperCase();
        if (cadCarros.verPlaca(placa)) {
            Carro car = cadCarros.getByDoc(placa);
            System.out.print("Deseja realmente atualizar este carro?"
                    + "\nProprietário: " + cadPessoas.getNomePes(car.getIdPessoa())
                    + "\nPlaca: " + car.getPlaca()
                    + "\nMarca: " + car.getMarca()
                    + "\nModelo: " + car.getModelo()
                    + "\nCor: " + car.getCor()
                    + "\nDigite 1 para sim, 0 para não: ");
            int opA = ler.nextInt();
            if (opA == 1) {
                boolean up;
                do {
                    System.out.print("Atualizações:"
                            + "\n1 - Cor"
                            + "\n2 - Proprietário"
                            + "\nDigite uma opção:");
                    int op = ler.nextInt();
                    switch (op) {
                        case 1:
                            System.out.print("Informe a cor: ");
                            car.setCor(leTexto().toUpperCase());
                            up = false;
                            break;
                        case 2:
                            boolean cpfPes;
                            do {
                                System.out.print("Informe o CPF do novo Proprietário: ");
                                String cpf = leTexto();
                                cpfPes = cadPessoas.verCPF(cpf);
                                if (cpfPes) {
                                    car.setIdPessoa(cadPessoas.pesqIdPes(cpf));
                                } else {
                                    System.out.println("CPF inválido, tente novamente!");
                                }
                            } while (!cpfPes);
                            up = false;
                            break;
                        default:
                            up = true;
                            System.out.println("Opção inválida!");
                            break;
                    }
                } while (up);
                System.out.println("Atualizaçõa  ");
            } else {
                System.out.println("Atualização cancelada!!!");
            }
        } else {
            System.out.println("Placa incorreta ou carro não cadastrado!!!");
        }
    }

    public static void msg() {
        System.out.print("|");
        System.out.print("-----------------------------------------------");
        System.out.println("|");
        System.out.print("|");
        System.out.print(" Não esqueçam que amanhã as 19h tem abertura   |"
                + "\n| da Feira de Oportunidades!                    |");
        System.out.print("\n|");
        System.out.print("-----------------------------------------------");
        System.out.println("|");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Calendar cal = GregorianCalendar.getInstance();
        int diaAtual = cal.get(Calendar.DAY_OF_MONTH);
        if (diaAtual == 14) {
            msg();
        }
        cadPessoas.mokPessoas();
        cadCarros.mokCarro();
        cadVagas.mokVagas();
        System.out.println(cadVagas.getAll().toString());
        int opM;
        String s = "s";

        do {
            menu();
            int m = ler.nextInt();
            switch (m) {
                case 1:
                    menuP();
                    opM = ler.nextInt();
                    switch (opM) {
                        case 1:
                            cadPessoa();
                            break;
                        case 2:
                            alteraStatus();
                            break;
                        case 3:
                            atualizaPessoa();
                            break;
                        case 4:
                            deletarPessoa();
                            break;
                        case 5:
                            imprimePessoas();
                            break;
                        case 0:
                            //voltar
                            break;
                        default:
                            System.out.println("Opção inválida, tente novamente!");
                            break;
                    }
                    break;
                case 2:
                    menuC();
                    opM = ler.nextInt();
                    //implementar o switch do carro
                    switch (opM) {
                        case 1:
                            cadCarro();
                            break;
                        case 2:
                            alterarCarro();
                            break;
                        case 3:
                            deletarCarro();
                            break;
                        case 4:
                            imprimirCarros();
                            break;
                        case 0:
                            //voltar
                            break;
                        default:
                            System.out.println("Opção inválida, tente novamente!");
                            break;
                    }
                    break;
                case 0:
                    s = "n";
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente!");
                    break;
            }

        } while (s.equalsIgnoreCase("s"));
        System.out.println("Aplicação encerrada pelo usuário!");

    }

}

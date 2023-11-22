package modelo;

import java.sql.*;

import dao.AulaDAO;
import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.ConnectionCreation;
import dao.InstrutorDAO;
import dao.PagamentoDAO;

import java.time.LocalDateTime;

public class Principal {
    
    public static void main(String args[]) throws SQLException {
        Cliente cliente1 = new Cliente("1111111", "11111111111", "21111111111", "Clientildo1", "Casa 1");
        Cliente cliente2 = new Cliente("2222222", "22222222222", "21222222222", "Clientildo2", "Casa 2");
        Cliente cliente3 = new Cliente("3333333", "33333333333", "21333333333", "Clientildo3", "Casa 3");
        Instrutor instrutor1 = new Instrutor("Tio Naldo Benny", "18273857341", 3000, "111");
        Instrutor instrutor2 = new Instrutor("Tio Chris Bumstead", "17283948392", 100000, "222");
        Instrutor instrutor3 = new Instrutor("Tia She-Ra", "19284758632", 50000, "333");
        Funcionario funcionario1 = new Funcionario("sdjnal", "21312312", 5000, "gerente", "1111");
        Funcionario funcionario2 = new Funcionario("Josislaine", "18274898726", 6000, "gerente", "2222");
        Pagamento pagamento1 = new Pagamento(1200, LocalDateTime.of(2023, 2, 27, 12, 30), "muay thai", "", "", 3);
        Pagamento pagamento2 = new Pagamento(2400, LocalDateTime.of(2023, 5, 21, 12, 30), "musculação", "", "", 6);
        Pagamento pagamento3 = new Pagamento(400, LocalDateTime.of(2023, 8, 12, 12, 30), "dança", "", "", 1);
        Aula aula1 = new Aula(LocalDateTime.of(2023, 11, 22, 17, 30), "musculação", "", "");
        Aula aula2 = new Aula(LocalDateTime.of(2023, 11, 23, 15, 30), "muay thai", "", "");
        Aula aula3 = new Aula(LocalDateTime.of(2023, 11, 24, 20, 30), "dança", "", "");
        
        ConnectionCreation connectionClass = new ConnectionCreation();
        Connection connection = connectionClass.criaConexao();
        
        ClienteDAO cdao = new ClienteDAO(connection);
        InstrutorDAO idao = new InstrutorDAO(connection);
        FuncionarioDAO fdao = new FuncionarioDAO(connection);
        PagamentoDAO pdao = new PagamentoDAO(connection);
        AulaDAO adao = new AulaDAO(connection);
        
        cdao.create(cliente1);
        cdao.create(cliente2);
        cdao.create(cliente3);
        
        System.out.println(cdao.retrieveAll());
        System.out.println(cdao.retrieveTelefones());
        
        fdao.create(funcionario1);
        fdao.create(funcionario2);
        
        System.out.println(fdao.retrieveAll());
        System.out.println(fdao.retrieveSalarios());
        
        idao.create(instrutor1);
        idao.create(instrutor2);
        idao.create(instrutor3);
        
        System.out.println(idao.retrieveAll());
        System.out.println(idao.retrieveSalarios());
        
        adao.create(aula1);
        adao.create(aula2);
        adao.create(aula3);
        
        System.out.println(adao.retrieveAll());
        System.out.println(adao.retrieveClientes());
        
        pdao.createComGerenteComCliente(pagamento1, funcionario1, cliente1);
        pdao.createComGerenteComCliente(pagamento2, funcionario2, cliente3);
        pdao.createComGerenteComCliente(pagamento3, funcionario2, cliente2);
        
        System.out.println(pdao.retrieveAll());
        System.out.println(pdao.retrieveDatas());
        
        
        
    }   
}
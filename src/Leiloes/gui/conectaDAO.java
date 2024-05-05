package Leiloes.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    
    private Connection conexao;

    public Connection getConexao() {
        return conexao;
    }

    public void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11", "root", "");
            System.out.println("CONECTADO COM SUCESSO!");
        } catch (ClassNotFoundException e) {
            System.out.println("Falha ao conectar com o bando de dados: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco" + e.getMessage());
        }
    }

    public void desconectar() {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão com o banco de dados fechada.");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            }
        }  
    }
}

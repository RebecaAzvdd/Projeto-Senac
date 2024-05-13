package Leiloes.gui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    public static boolean cadastrar(ProdutosDTO p) throws SQLException {
        try {
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();

            String sql = "INSERT INTO produtos(nome, valor, status) VALUES(?, ?, ?);";
            PreparedStatement query = conexao.getConexao().prepareStatement(sql);
            query.setString(1, p.getNome());
            query.setInt(2, p.getValor());
            query.setString(3, p.getStatus());

            query.execute();

            conexao.desconectar();
            return true;
        } catch (SQLException se) {
            System.out.println(se);
            return false;
        }
    }

    public static List<ProdutosDTO> listar() {
        List<ProdutosDTO> lista = new ArrayList<ProdutosDTO>();

        try {
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();

            String sql = "select * from produtos;";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);
            ResultSet resposta = consulta.executeQuery();

            while (resposta.next()) {
                ProdutosDTO p = new ProdutosDTO();

                p.setId(resposta.getInt("id"));
                p.setNome(resposta.getString("nome"));
                p.setValor(resposta.getInt("valor"));
                p.setStatus(resposta.getString("status"));

                lista.add(p);
            }
            conexao.desconectar();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return lista;
    }

    public static ProdutosDTO buscarPorId(int id) {
        ProdutosDTO p = new ProdutosDTO();

        try {
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();

            String sql = "select * from produtos where id=?;";

            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);

            consulta.setInt(1, id);

            ResultSet resposta = consulta.executeQuery();

            if (resposta.next()) {

                p.setId(resposta.getInt("id"));
                p.setNome(resposta.getString("nome"));
                p.setValor(resposta.getInt("valor"));
                p.setStatus(resposta.getString("status"));

            }
            conexao.desconectar();

        } catch (SQLException se) {

        }
        return p;
    }

    public static List<ProdutosDTO> listarVendas() {
        List<ProdutosDTO> listaV = new ArrayList<ProdutosDTO>();

        try {
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();

            String sql = "select * from produtos WHERE status= ?;";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);
            consulta.setString(1, "Vendido");

            ResultSet resposta = consulta.executeQuery();

            while (resposta.next()) {
                ProdutosDTO p = new ProdutosDTO();

                p.setId(resposta.getInt("id"));
                p.setNome(resposta.getString("nome"));
                p.setValor(resposta.getInt("valor"));
                p.setStatus(resposta.getString("status"));

                listaV.add(p);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return listaV;
    }

    public static void venderProduto(int id) throws SQLException {
        
        try {
            conectaDAO conexao = new conectaDAO();
           conexao.conectar();
           String sql = "UPDATE produtos SET status = ? WHERE id = ?";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);
             consulta.setString(1, "Vendido");
            consulta.setInt(2, id);


            int linhasAfetadas = consulta.executeUpdate();

           if (linhasAfetadas == 0) {
                JOptionPane.showMessageDialog(null, "Não a produto com esse id");
           }
        }catch(SQLException se){
            se.printStackTrace();
        }
            }
        }

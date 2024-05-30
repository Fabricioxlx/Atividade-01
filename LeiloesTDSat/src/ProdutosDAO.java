/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void venderProduto(int id){
        conn = new conectaDAO().connectDB();
        String sql = "update produtos set status = \"Vendido\" where id = ?";
        try{
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            prep.execute();
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Produto não vendido");
        }
    }
    public void cadastrarProduto (ProdutosDTO produto){
        conn = new conectaDAO().connectDB();
        String sql = "insert into produtos (nome, valor, status) Values (?, ?, ?)";
        try{
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.execute();
            JOptionPane.showMessageDialog(null, "O Produto foi cadastrado com sucesso!");
        } catch(SQLException e) {
            System.out.println("Erro ao Adicionar Produto: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "O Produto não pôde ser cadastrado. Tente novamente mais tarde!");
        } catch (java.lang.NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Digite um valor válido!");
        }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        conn = new conectaDAO().connectDB();
        String sql = "Select * from produtos";
        try{
            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();
            
            while(rs.next()){
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException e) {
                return null;
        }
        return listagem;
    }
    
    
    
        
}


import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.List;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean venderProduto(int idProduto) {
        conectaDAO cdao = new conectaDAO();
        Connection con = cdao.connectDB();
        
    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idProduto);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public List<ProdutosDTO> listarProdutosVendidos() {
    List<ProdutosDTO> vendidos = new ArrayList<>();
    conectaDAO cdao = new conectaDAO();
    Connection con = cdao.connectDB();
    
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
    
    try (PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
         
        while (rs.next()) {
            ProdutosDTO p = new ProdutosDTO();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setValor(rs.getInt("valor"));
            p.setStatus(rs.getString("status"));
            vendidos.add(p);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return vendidos;
}
    
    public boolean cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        try {
            conectaDAO cdao = new conectaDAO();
            Connection con = cdao.connectDB();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, produto.getNome());
            pst.setInt(2, produto.getValor());
            pst.setString(3, produto.getStatus());

            int result = pst.executeUpdate();
            con.close();
            return result > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        listagem.clear();
        String sql = "SELECT * FROM produtos";
        try {
            conectaDAO cdao = new conectaDAO();
            Connection con = cdao.connectDB();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + ex.getMessage());
        }
        return listagem;
    }
}

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Inventario {

    private static final String URL = "jdbc:sqlite:inventario.db";
    private Connection conn;

    public Inventario() {
        try {
            conn = DriverManager.getConnection(URL);
            crearTablaSiNoExiste();
        } catch (SQLException e) {
            System.out.println("Error conectando a la base de datos: " + e.getMessage());
        }
    }

    private void crearTablaSiNoExiste() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS productos (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "nombre TEXT NOT NULL," +
                     "precio REAL NOT NULL," +
                     "cantidad INTEGER NOT NULL" +
                     ");";
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }

    public void agregarProducto(Producto producto) {
        String sql = "INSERT INTO productos(nombre, precio, cantidad) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, producto.getNombre());
            pstmt.setDouble(2, producto.getPrecio());
            pstmt.setInt(3, producto.getCantidad());
            pstmt.executeUpdate();
            System.out.println("Producto agregado: " + producto.getNombre());
        } catch (SQLException e) {
            System.out.println("Error agregando producto: " + e.getMessage());
        }
    }

    public List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto p = new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getInt("cantidad")
                );
                productos.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error listando productos: " + e.getMessage());
        }
        return productos;
    }
}

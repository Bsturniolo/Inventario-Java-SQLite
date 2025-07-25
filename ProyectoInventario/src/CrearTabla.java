import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class CrearTabla {

    public static void crearTabla() {
        String url = "jdbc:sqlite:inventario.db";

        String sql = "CREATE TABLE IF NOT EXISTS productos ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + "nombre TEXT NOT NULL, "
                   + "precio REAL NOT NULL, "
                   + "cantidad INTEGER NOT NULL"
                   + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabla 'productos' verificada o creada exitosamente.");

        } catch (SQLException e) {
            System.err.println("Error al crear la tabla: " + e.getMessage());
        }
    }
}

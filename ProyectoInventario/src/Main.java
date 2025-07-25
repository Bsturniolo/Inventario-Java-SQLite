import java.util.List;

public class Main {
    public static void main(String[] args) {
        CrearTabla.crearTabla();

        ProductoDAO dao = new ProductoDAO();

        // Crear productos
        dao.agregarProducto(new Producto(0, "Manzanas", 120.5, 10));
        dao.agregarProducto(new Producto(0, "Naranjas", 150.0, 20));

        // Listar productos
        List<Producto> productos = dao.listarProductos();
        System.out.println("Productos actuales:");
        for (Producto p : productos) {
            System.out.println(p);
        }

        // Actualizar un producto (ejemplo)
        Producto p1 = productos.get(0);
        p1.setCantidad(15);
        dao.actualizarProducto(p1);

        // Eliminar un producto (ejemplo)
        if (productos.size() > 1) {
            dao.eliminarProducto(productos.get(1).getId());
        }

        // Listar productos después de cambios
        System.out.println("Productos después de modificaciones:");
        dao.listarProductos().forEach(System.out::println);
    }
}

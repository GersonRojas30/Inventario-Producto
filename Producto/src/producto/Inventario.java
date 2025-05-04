package producto;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JOptionPane;

public class Inventario {
    private Queue<Producto> productos;

    public Inventario() {
        this.productos = new LinkedList<>();
    }

    public void registrarProducto(String nombre, String codigo) {
        if (nombre == null || nombre.trim().isEmpty() || codigo == null || codigo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nombre o código inválido. No se registró el producto.");
            return;
        }
        Producto producto = new Producto(nombre.trim(), codigo.trim());
        productos.add(producto);
        JOptionPane.showMessageDialog(null, "Producto registrado con éxito.");
    }

    public void despacharProducto() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos en el inventario.");
        } else {
            Producto producto = productos.poll();
            JOptionPane.showMessageDialog(null, "Producto despachado: " + producto.getNombre() +
                                                " (Código: " + producto.getCodigo() + ")");
        }
    }

    public void verProductos() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El inventario está vacío.");
        } else {
            StringBuilder mensaje = new StringBuilder("Productos en inventario:\n");
            for (Producto producto : productos) {
                mensaje.append("- ").append(producto.getNombre())
                       .append(" (").append(producto.getCodigo()).append(")\n");
            }
            JOptionPane.showMessageDialog(null, mensaje.toString());
        }
    }

    public void eliminarProductoPorCodigo(String codigo) {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El inventario está vacío.");
            return;
        }

        Queue<Producto> temp = new LinkedList<>();
        boolean encontrado = false;

        while (!productos.isEmpty()) {
            Producto p = productos.poll();
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                encontrado = true;
                JOptionPane.showMessageDialog(null, "Producto eliminado: " + p.getNombre() + " (Código: " + p.getCodigo() + ")");
            } else {
                temp.add(p);
            }
        }

        productos = temp;

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "No se encontró ningún producto con ese código.");
        }
    }

    public void ejecutar() {
        while (true) {
            String input = JOptionPane.showInputDialog(
                    "1: Registrar nuevo Producto\n" +
                    "2: Despachar Producto (FIFO)\n" +
                    "3: Ver Productos en Inventario\n" +
                    "4: Eliminar Producto por Código\n" +
                    "5: Salir\nIngrese su opción:");

            if (input == null) {
                JOptionPane.showMessageDialog(null, "Saliendo...");
                return;
            }

            int opcion;
            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese una opción válida.");
                continue;
            }

            switch (opcion) {
                case 1:
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
                    String codigo = JOptionPane.showInputDialog("Ingrese el código del producto:");
                    registrarProducto(nombre, codigo);
                    break;
                case 2:
                    despacharProducto();
                    break;
                case 3:
                    verProductos();
                    break;
                case 4:
                    String codEliminar = JOptionPane.showInputDialog("Ingrese el código del producto a eliminar:");
                    eliminarProductoPorCodigo(codEliminar);
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }

    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        inventario.ejecutar();
    }
}
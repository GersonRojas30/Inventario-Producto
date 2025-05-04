/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package producto;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JOptionPane;
/**
 *
 * @author USUARIO
 */
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
        JOptionPane.showMessageDialog(null, " Producto registrado con éxito.");
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

    public void ejecutar() {
        while (true) {
            String input = JOptionPane.showInputDialog(
                    "1: Registrar nuevo Producto\n" +
                    "2: Despachar Producto (FIFO)\n" +
                    "3: Ver Productos en Inventario\n" +
                    "4: Salir\nIngrese su opción:");

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
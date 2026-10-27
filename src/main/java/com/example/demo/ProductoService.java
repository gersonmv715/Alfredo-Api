package com.example.demo;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
        // Inicializamos algunos datos de ejemplo
        initSampleData();
    }

    private void initSampleData() {
        save(new Producto("Sudado montañero", "Sudado con papa y yuca", 28000, "plato"));
        save(new Producto("Jugo de lulo", "Jugo natural en agua", 7000, "bebida"));
        save(new Producto("Postre de tres leches", "Porción individual", 9000, "postre"));
    }

    // Crear un nuevo producto
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    // Obtener un producto por ID
    public Producto findById(String id) {
        return productoRepository.findById(id);
    }

    // Listar todos los productos
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    // Buscar por nombre
    public List<Producto> findByNombre(String nombre) {
        return productoRepository.findByNombreContaining(nombre);
    }

    // Buscar por categoría
    public List<Producto> findByCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    // Actualizar un producto
    public Producto update(Producto producto) {
        return productoRepository.update(producto);
    }

    // Actualización parcial
    public Producto patch(String id, Map<String, Object> updates) {
        Producto producto = productoRepository.findById(id);
        if (producto != null) {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "nombre":
                        producto.setNombre((String) value);
                        break;
                    case "descripcion":
                        producto.setDescripcion((String) value);
                        break;
                    case "precio":
                        // Number sirve tanto para 28000 (Integer) como para 28000.5 (Double)
                        producto.setPrecio(((Number) value).doubleValue());
                        break;
                    case "categoria":
                        producto.setCategoria((String) value);
                        break;
                    case "disponible":
                        producto.setDisponible((Boolean) value);
                        break;
                }
            });
            return productoRepository.update(producto);
        }
        return null;
    }

    // Eliminar un producto
    public void deleteById(String id) {
        productoRepository.deleteById(id);
    }
}
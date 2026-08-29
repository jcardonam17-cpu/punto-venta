package com.punto.venta.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.punto.venta.dto.ProductoDTO;
import com.punto.venta.service.ProductoService;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController {
    private final ProductoService productoService;
    public ProductoController(ProductoService productoService) { this.productoService = productoService; }
    @GetMapping public List<ProductoDTO> listarTodos() { return productoService.listarProductos(); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public ProductoDTO crearProducto(@RequestBody ProductoDTO dto) { return productoService.crear(dto); }
    @PutMapping("/{id}") public ProductoDTO actualizar(@PathVariable Integer id, @RequestBody ProductoDTO dto) { return productoService.actualizar(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) { productoService.eliminar(id); }
    @PutMapping("/{id}/anular") public ProductoDTO anular(@PathVariable Integer id) { return productoService.anular(id); }
}

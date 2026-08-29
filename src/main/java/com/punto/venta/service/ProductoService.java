package com.punto.venta.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.punto.venta.dto.ProductoDTO;
import com.punto.venta.entity.Categoria;
import com.punto.venta.entity.Producto;
import com.punto.venta.repository.CategoriaRepository;
import com.punto.venta.repository.ProductoRepository;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository; private final CategoriaRepository categoriaRepository;
    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) { this.productoRepository=productoRepository; this.categoriaRepository=categoriaRepository; }
    public List<ProductoDTO> listarProductos(){return productoRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());}
    public ProductoDTO crear(ProductoDTO dto){Categoria c=categoriaRepository.findById(dto.getIdCategoria()).orElseThrow(()->new RuntimeException("La categoría no existe")); Producto p=convertToEntity(dto); p.setIdCategoria(c); return convertToDTO(productoRepository.save(p));}
    public ProductoDTO actualizar(Integer id, ProductoDTO dto){Producto p=productoRepository.findById(id).orElseThrow(()->new RuntimeException("El producto no existe")); if(dto.getIdCategoria()!=null){Categoria c=categoriaRepository.findById(dto.getIdCategoria()).orElseThrow(()->new RuntimeException("La categoría no existe")); p.setIdCategoria(c);} p.setNombre(dto.getNombre()); p.setDescripcion(dto.getDescripcion()); p.setPrecio(dto.getPrecio()); p.setStock(dto.getStock()); if(dto.getEstado()!=null)p.setEstado(dto.getEstado()); return convertToDTO(productoRepository.save(p));}
    public void eliminar(Integer id){Producto p=productoRepository.findById(id).orElseThrow(()->new RuntimeException("El producto no existe")); productoRepository.delete(p);}
    public ProductoDTO anular(Integer id){Producto p=productoRepository.findById(id).orElseThrow(()->new RuntimeException("El producto no existe")); p.setEstado(false); return convertToDTO(productoRepository.save(p));}
    private ProductoDTO convertToDTO(Producto p){ProductoDTO dto=new ProductoDTO(); dto.setIdProducto(p.getIdProducto()); dto.setEstado(p.getEstado()); dto.setNombre(p.getNombre()); dto.setDescripcion(p.getDescripcion()); dto.setPrecio(p.getPrecio()); dto.setStock(p.getStock()); if(p.getIdCategoria()!=null)dto.setIdCategoria(p.getIdCategoria().getIdCategoria()); return dto;}
    private Producto convertToEntity(ProductoDTO dto){Producto p=new Producto(); p.setEstado(dto.getEstado()!=null?dto.getEstado():true); p.setNombre(dto.getNombre()); p.setDescripcion(dto.getDescripcion()); p.setPrecio(dto.getPrecio()); p.setStock(dto.getStock()); return p;}
}

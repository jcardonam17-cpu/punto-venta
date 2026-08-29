package com.punto.venta.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.punto.venta.dto.CategoriaDTO;
import com.punto.venta.entity.Categoria;
import com.punto.venta.repository.CategoriaRepository;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    public CategoriaService(CategoriaRepository categoriaRepository) { this.categoriaRepository = categoriaRepository; }
    public List<CategoriaDTO> listarCategorias() { return categoriaRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList()); }
    public CategoriaDTO crear(CategoriaDTO dto) { Categoria c=new Categoria(); c.setEstado(dto.getEstado()!=null?dto.getEstado():true); c.setNombre(dto.getNombre()); c.setDescripcion(dto.getDescripcion()); return convertirADTO(categoriaRepository.save(c)); }
    public CategoriaDTO actualizar(Integer id, CategoriaDTO dto) { Categoria c=categoriaRepository.findById(id).orElseThrow(()->new RuntimeException("La categoría no existe")); c.setNombre(dto.getNombre()); c.setDescripcion(dto.getDescripcion()); if(dto.getEstado()!=null)c.setEstado(dto.getEstado()); return convertirADTO(categoriaRepository.save(c)); }
    public void eliminar(Integer id) { Categoria c=categoriaRepository.findById(id).orElseThrow(()->new RuntimeException("La categoría no existe")); categoriaRepository.delete(c); }
    public CategoriaDTO anular(Integer id) { Categoria c=categoriaRepository.findById(id).orElseThrow(()->new RuntimeException("La categoría no existe")); c.setEstado(false); return convertirADTO(categoriaRepository.save(c)); }
    private CategoriaDTO convertirADTO(Categoria c) { CategoriaDTO dto=new CategoriaDTO(); dto.setIdCategoria(c.getIdCategoria()); dto.setEstado(c.getEstado()); dto.setNombre(c.getNombre()); dto.setDescripcion(c.getDescripcion()); return dto; }
}

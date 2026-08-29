package com.punto.venta.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.punto.venta.dto.CategoriaDTO;
import com.punto.venta.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<CategoriaDTO> listarTodas() { return categoriaService.listarCategorias(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaDTO crearCategoria(@RequestBody CategoriaDTO categoriaDTO) { return categoriaService.crear(categoriaDTO); }

    @PutMapping("/{id}")
    public CategoriaDTO actualizar(@PathVariable Integer id, @RequestBody CategoriaDTO dto) { return categoriaService.actualizar(id, dto); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) { categoriaService.eliminar(id); }

    @PutMapping("/{id}/anular")
    public CategoriaDTO anular(@PathVariable Integer id) { return categoriaService.anular(id); }
}

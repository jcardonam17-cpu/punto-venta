package com.punto.venta.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.punto.venta.dto.PedidoDTO;
import com.punto.venta.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {
    private final PedidoService pedidoService;
    public PedidoController(PedidoService pedidoService) { this.pedidoService = pedidoService; }
    @GetMapping public List<PedidoDTO> listarTodos() { return pedidoService.listarPedidos(); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO crearPedido(@RequestBody PedidoDTO dto) { return pedidoService.crear(dto); }
    @PutMapping("/{id}") public PedidoDTO actualizar(@PathVariable Integer id, @RequestBody PedidoDTO dto) { return pedidoService.actualizar(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) { pedidoService.eliminar(id); }
    @PutMapping("/{id}/anular") public PedidoDTO anular(@PathVariable Integer id) { return pedidoService.anular(id); }
}

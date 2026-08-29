package com.punto.venta.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.punto.venta.dto.PedidoDetalleDTO;
import com.punto.venta.service.PedidoDetalleService;

@RestController
@RequestMapping("/pedidos-detalles")
@CrossOrigin(origins = "*")
public class PedidoDetalleController {
    private final PedidoDetalleService pedidoDetalleService;
    public PedidoDetalleController(PedidoDetalleService pedidoDetalleService) { this.pedidoDetalleService = pedidoDetalleService; }
    @GetMapping public List<PedidoDetalleDTO> listarTodos() { return pedidoDetalleService.listarDetalles(); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public PedidoDetalleDTO crearDetalle(@RequestBody PedidoDetalleDTO dto) { return pedidoDetalleService.crear(dto); }
    @PutMapping("/{id}") public PedidoDetalleDTO actualizar(@PathVariable Integer id, @RequestBody PedidoDetalleDTO dto) { return pedidoDetalleService.actualizar(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) { pedidoDetalleService.eliminar(id); }
    @PutMapping("/{id}/anular") public PedidoDetalleDTO anular(@PathVariable Integer id) { return pedidoDetalleService.anular(id); }
}

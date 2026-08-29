package com.punto.venta.service;

import java.util.Date; import java.util.List; import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.punto.venta.dto.PedidoDTO; import com.punto.venta.entity.Cliente; import com.punto.venta.entity.Pedido; import com.punto.venta.repository.ClienteRepository; import com.punto.venta.repository.PedidoRepository;

@Service
public class PedidoService {
 private final PedidoRepository pedidoRepository; private final ClienteRepository clienteRepository;
 public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository){this.pedidoRepository=pedidoRepository;this.clienteRepository=clienteRepository;}
 public List<PedidoDTO> listarPedidos(){return pedidoRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());}
 public PedidoDTO crear(PedidoDTO dto){Cliente c=clienteRepository.findById(dto.getIdCliente()).orElseThrow(()->new RuntimeException("El cliente no existe")); Pedido p=new Pedido(); p.setEstado(dto.getEstado()!=null?dto.getEstado():true); p.setFechaPedido(dto.getFechaPedido()!=null?dto.getFechaPedido():new Date()); p.setEstadoPedido(dto.getEstadoPedido()!=null?dto.getEstadoPedido():true); p.setTotal(dto.getTotal()); p.setIdCliente(c); return convertirADTO(pedidoRepository.save(p));}
 public PedidoDTO actualizar(Integer id, PedidoDTO dto){Pedido p=pedidoRepository.findById(id).orElseThrow(()->new RuntimeException("El pedido no existe")); if(dto.getIdCliente()!=null){Cliente c=clienteRepository.findById(dto.getIdCliente()).orElseThrow(()->new RuntimeException("El cliente no existe"));p.setIdCliente(c);} if(dto.getFechaPedido()!=null)p.setFechaPedido(dto.getFechaPedido()); if(dto.getTotal()!=null)p.setTotal(dto.getTotal()); if(dto.getEstado()!=null)p.setEstado(dto.getEstado()); if(dto.getEstadoPedido()!=null)p.setEstadoPedido(dto.getEstadoPedido()); return convertirADTO(pedidoRepository.save(p));}
 public void eliminar(Integer id){Pedido p=pedidoRepository.findById(id).orElseThrow(()->new RuntimeException("El pedido no existe"));pedidoRepository.delete(p);}
 public PedidoDTO anular(Integer id){Pedido p=pedidoRepository.findById(id).orElseThrow(()->new RuntimeException("El pedido no existe"));p.setEstado(false);p.setEstadoPedido(false);return convertirADTO(pedidoRepository.save(p));}
 private PedidoDTO convertirADTO(Pedido p){PedidoDTO dto=new PedidoDTO();dto.setIdPedido(p.getIdPedido());dto.setEstado(p.getEstado());dto.setFechaPedido(p.getFechaPedido());dto.setEstadoPedido(p.getEstadoPedido());dto.setTotal(p.getTotal());if(p.getIdCliente()!=null)dto.setIdCliente(p.getIdCliente().getIdCliente());return dto;}
}

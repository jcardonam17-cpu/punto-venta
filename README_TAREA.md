# Métodos PUT, DELETE y ANULAR - Punto de Venta

Se agregaron los métodos solicitados para Categoría, Producto, Pedido y PedidoDetalle.

## Endpoints

### Categorías
- PUT `http://localhost:8080/categorias/{id}`
- DELETE `http://localhost:8080/categorias/{id}`
- PUT `http://localhost:8080/categorias/{id}/anular`

### Productos
- PUT `http://localhost:8080/productos/{id}`
- DELETE `http://localhost:8080/productos/{id}`
- PUT `http://localhost:8080/productos/{id}/anular`

### Pedidos
- PUT `http://localhost:8080/pedidos/{id}`
- DELETE `http://localhost:8080/pedidos/{id}`
- PUT `http://localhost:8080/pedidos/{id}/anular`

### PedidoDetalle
- PUT `http://localhost:8080/pedidos-detalles/{id}`
- DELETE `http://localhost:8080/pedidos-detalles/{id}`
- PUT `http://localhost:8080/pedidos-detalles/{id}/anular`

## Importante: PedidoDetalle

La entidad `pedido_detalle` del proyecto original no tenía una columna `ESTADO`. Para implementar ANULAR de la misma forma que las demás tablas (baja lógica con `estado=false`), se agregó el atributo `estado` a `PedidoDetalle` y `PedidoDetalleDTO`.

Antes de probar los endpoints de PedidoDetalle, ejecutar en MySQL:

```sql
ALTER TABLE pedido_detalle ADD COLUMN ESTADO BOOLEAN DEFAULT TRUE;
UPDATE pedido_detalle SET ESTADO = TRUE WHERE ESTADO IS NULL;
```

Si la columna ya existe en la base de datos, no ejecutar el `ALTER TABLE`.

## Diferencia entre DELETE y ANULAR

- DELETE elimina físicamente el registro de la base de datos.
- ANULAR conserva el registro y cambia `estado` a `false`.

Para Pedido, ANULAR también coloca `estadoPedido=false`.

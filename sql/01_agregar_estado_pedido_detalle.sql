-- Ejecutar una sola vez en la base de datos punto_venta.
-- Si la columna ESTADO ya existe en pedido_detalle, no ejecutar esta instrucción.
ALTER TABLE pedido_detalle ADD COLUMN ESTADO BOOLEAN DEFAULT TRUE;

-- Opcional: activar los registros existentes.
UPDATE pedido_detalle SET ESTADO = TRUE WHERE ESTADO IS NULL;

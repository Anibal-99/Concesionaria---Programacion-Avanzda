SELECT
    CONCAT(marca.nombre, ' ', modelo.nombre) as "MODELO",
    SUM(auto.costo * venta.cantidad) as "COSTO TOTAL",
    SUM(venta.monto_total) as "PRECIO TOTAL",
    SUM(venta.monto_total - (auto.costo * venta.cantidad)) as ganancia,
    SUM(venta.cantidad) as ventas
FROM
    auto INNER JOIN modelo ON auto.modelo_id = modelo.id
    INNER JOIN marca ON modelo.marca_id = marca.id
    INNER JOIN venta ON venta.auto_id = auto.id
WHERE
    extract(year from to_date(venta.fecha_venta, 'DD/MM/YYYY'))=2022
GROUP BY
    modelo.nombre,
    marca.nombre
ORDER BY
    ganancia
DESC
;

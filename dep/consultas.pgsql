/* Listado de cantidad de autos mas vendidos en el ultimo año */
select auto.id as "ID", concat(marca.nombre, ' ', modelo.nombre, ' ', modelo.anio) as "Auto", venta.cantidad as "Cantidad", auto.costo as "Costo", venta.monto_total as "Total", venta.monto_total- (Costo*Cantidad) as "Ganancia", to_date(venta.fecha_venta, 'DD/MM/YYYY') as "Fecha" from auto inner join modelo on auto.modelo_id=modelo.id inner join marca on modelo.marca_id=marca.id left join venta on venta.auto_id=auto.id where extract(year from to_date(venta.fecha_venta, 'DD/MM/YYYY'))='2022';


/*Listado de cantidad de marcas vendidas por region.*/
select
venta.auto_id as "ID",
marca.nombre as "Nombre",
pais.nombre as "Pais",
region.nombre as "Region"
from
marca
inner join
pais
on marca.pais_id=pais.id
inner join
region
on pais.region_id=region.id
left join
modelo
on modelo.marca_id=marca.id
left join
auto
on auto.modelo_id=modelo.id
inner join
venta
on venta.auto_id=auto.id
order by
marca.nombre desc; /*tambien se puede ordenar por region_id*/

package Modelos;

public class Modelo {
    int id;
    String nombre;
    int anio;
    Marca marca;

    public Modelo() {
    }

    public Modelo(int id, String nombre, int anio, Marca marca) {
        this.id = id;
        this.nombre = nombre;
        this.anio = anio;
        this.marca = marca;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return this.getMarca().getName() + " " + this.getNombre() + " " + this.getAnio();
    }

}

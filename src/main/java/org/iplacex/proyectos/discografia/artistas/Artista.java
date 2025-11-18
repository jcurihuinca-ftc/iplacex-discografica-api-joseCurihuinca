package org.iplacex.proyectos.discografia.artistas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "artistas")
public class Artista {
    @Id
    public String _id;

    public String nombre;
    public String pais;
    public String genero;
    public int anio_formacion;

    public Artista() {}

    public Artista(String nombre, String pais, String genero, int anio_formacion) {
        this.nombre = nombre;
        this.pais = pais;
        this.genero = genero;
        this.anio_formacion = anio_formacion;
    }

    // Getters y Setters
    public String get_id() { return _id; }
    public void set_id(String _id) { this._id = _id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public int getAnio_formacion() { return anio_formacion; }
    public void setAnio_formacion(int anio_formacion) { this.anio_formacion = anio_formacion; }
}

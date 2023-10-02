package modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private String contrasenna;
    private String id;

    //Construye el nuevo usuario a partir de los datos dados con un id manual
    public Usuario(String nombre, String contrasenna, String id) {
        this.nombre = nombre;
        this.contrasenna = contrasenna;
        this.id = id;

    }

    //Construye el nuevo usuario a partir de los datos dados con un id automatico
    public Usuario(String nombre, String contrasenna) {
        id = "-1";
        this.nombre = nombre;
        this.contrasenna = contrasenna;

        // TODO Auto-generated constructor stub
    }

    public String getId(){
        return id;
    }

    public Usuario(String nombre) {
        id = "-1";
        this.nombre = nombre;
        this.contrasenna = "";

        // TODO Auto-generated constructor stub
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContraseña(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public boolean comprobar(String nombre, String password) {
        // TODO Auto-generated method stub
        return (this.nombre.equals(nombre) && this.contrasenna.equals(password));
    }

    public boolean comprobarNombre(String nombre) {
        // TODO Auto-generated method stub
        return (this.nombre.equals(nombre));
    }

    //Pasa los datos del usuario, principalmente para usos de pruebas
    public String imprimir() {
        return id + "," + nombre + "," + contrasenna;
    }

    public static Usuario parserUsuario(String linea) {
        String[] datos = linea.split(",");
        if (datos.length == 3) {
            return new Usuario(datos[1], datos[2], datos[0]);
        }

        return null;
    }

}

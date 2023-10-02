package util;

import java.util.ArrayList;

import modelo.Usuario;


public class BBDD {
    private ArrayList<Usuario> usuarios;



    public BBDD() {

        generarUsuarios();
    //    generarConceptosDeGasto();

    }





    private void generarUsuarios() {
        // TODO Auto-generated method stub
        usuarios=new ArrayList<Usuario>();

        usuarios.add(new Usuario("juan","1234"));
        usuarios.add(new Usuario("j","1"));

    }

    private boolean annadirUsuario(String nombre, String contrasenna) {
        // TODO Auto-generated method stub

        for (int i = 0; i<usuarios.size(); i++){
            if (nombre.equals(usuarios.get(i).getNombre())){
                return false;
            }
        }
        usuarios.add(new Usuario(nombre,contrasenna));
        return true;
    }
/*
    private void generarConceptosDeGasto() {
        // TODO Auto-generated method stub
        conceptosDeGasto=new ArrayList<ConceptoDeGasto>();

        conceptosDeGasto.add(new ConceptoDeGasto("Alimentos"));
        conceptosDeGasto.add(new ConceptoDeGasto("Gasto profesional"));
        conceptosDeGasto.add(new ConceptoDeGasto("Ocio"));
        conceptosDeGasto.add(new ConceptoDeGasto("Lujos"));
        conceptosDeGasto.add(new ConceptoDeGasto("Gasto domestico"));

    }
*/




    public ArrayList<Usuario> getUsuarios() {
        // TODO Auto-generated method stub
        return usuarios;
    }
}

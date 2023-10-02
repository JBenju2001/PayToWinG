package modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

//public class BBDD extends SQLiteOpenHelper {
/*
    //Inicializa las variables
    private static final String NOMBRE_BD = "Gastos.db";
    private static final int VERSION_BD = 8;
    private static final String NOMBRE_TABLA_GASTOS = "gastos";
    private static final String CREAR_TABLA_GASTOS =
            "CREATE TABLE " + NOMBRE_TABLA_GASTOS +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "id_usario TEXT, " +
                    "cantidad REAL," +
                    "concepto TEXT," +
                    "descripcion TEXT," +
                    "fecha TEXT," +
                    "fechaM TEXT)";
    private SQLiteDatabase bd;

    public BBDD(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
        bd = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(CREAR_TABLA_GASTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int versionAnterior, int versionNueva) {
        bd.execSQL("DROP TABLE IF EXISTS " + NOMBRE_TABLA_GASTOS);
        onCreate(bd);
    }

    public void insertarGasto(Gasto g) {
        ContentValues cv = new ContentValues();
        cv.put("id_usario", g.getId_usuario());
        cv.put("cantidad", g.getCantidad());
        cv.put("concepto", g.getConcepto());
        cv.put("fecha",g.getFecha());
        cv.put("fechaM",g.getFechaM());
    //    cv.put("anyo",g.getAño());
     //   cv.put("mes",g.getMes());
        //  cv.put("dia",g.getDia());

        cv.put("descripcion", g.getDescripcion());
        bd.insert(NOMBRE_TABLA_GASTOS, null, cv);
    }

    //Cambia el gasto selecciondo can datos nuevos
    public void actualizarGasto(Gasto g) {
        ContentValues cv = new ContentValues();
        cv.put("id_usario", g.getId_usuario());
        cv.put("cantidad", g.getCantidad());
        cv.put("concepto", g.getConcepto());
        cv.put("fecha",g.getFecha());
        cv.put("fechaM",g.getFechaM());
        cv.put("descripcion", g.getDescripcion());
        bd.update(NOMBRE_TABLA_GASTOS, cv,  " _id = " + g.getId(), null);
    }

    //Elimina el gasto selecciondo
    public void borrarGasto(int id) {
        // Elimina la nota seleccionada
        bd.delete(NOMBRE_TABLA_GASTOS, " _id = " + id, null);
    }

    //Cierra la base de datos
    public void cerrar() {
        // Cierra la base de datos
        bd.close();
    }

    //Obtienetodos los gastos
    public ArrayList<Gasto> obtenerGasto(String id_usuario) {
        ArrayList<Gasto> gastos = new ArrayList<Gasto>();

        String sentenciaSql = "SELECT *" +
                "" + " FROM " + NOMBRE_TABLA_GASTOS +
                " WHERE id_usario LIKE '"+id_usuario+"'";
        Cursor c=null;
       try {
           c = bd.rawQuery(sentenciaSql, null);
       }
       catch (Exception e){
           return null;
       }
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                int id = c.getInt(c.getColumnIndexOrThrow("_id"));


                String cantidad = c.getString(c.getColumnIndexOrThrow("cantidad"));
                String concepto = c.getString(c.getColumnIndexOrThrow("concepto"));
                String fecha=  c.getString(c.getColumnIndexOrThrow("fecha"));
                String descripcion = c.getString(c.getColumnIndexOrThrow("descripcion"));



                Gasto gasto = new Gasto(id, id_usuario, Double.parseDouble(cantidad), concepto, fecha, descripcion);
                gastos.add(gasto);
            } while (c.moveToNext());
        }
        c.close();
        return gastos;
    }

    //Obtiene la suma de la cantidad de todos los gastos que esten en el mes y el año seleccionados
    public double obtenerSumaGastoMes(String id_usuario, String mes, String año) {
        ArrayList<Gasto> gastos =this.obtenerGasto(id_usuario);
        double suma=0;
        if (mes.length()==1) mes="0"+mes;
        for (Gasto g : gastos){
            if (g.getMes().equals(mes) && g.getAño().equals(año)){
                suma+=g.getCantidad();
            }
        }
        return suma;
    }

    //Obtiene todos los gastos que esten entre las fechas elegidas
    public ArrayList<Gasto> obtenerGasto(String id_usuario, String fecha1, String fecha2) {


        ArrayList<Gasto> gastos =this.obtenerGasto(id_usuario);
        ArrayList<Gasto> gastosFiltrado=new ArrayList<Gasto>();
        for (Gasto g : gastos){
            if (g.estaEntre(fecha1,fecha2)){
                gastosFiltrado.add(g);
            }
        }
        return gastosFiltrado;
    }

    //Obtiene todos los gastos que esten entre las fechas elegidas y tengan el concepto elegido
    public ArrayList<Gasto> obtenerGasto(String id_usuario, String fecha1, String fecha2, String concepto) {
        try{
            ArrayList<Gasto> gastos =this.obtenerGasto(id_usuario);
            ArrayList<Gasto> gastosFiltrado=new ArrayList<Gasto>();
            for (Gasto g : gastos){
                if (g.estaEntre(fecha1,fecha2) && g.getConcepto().equals(concepto)){
                    gastosFiltrado.add(g);
                }
            }
            return gastosFiltrado;
        } catch (Exception e) {
            return obtenerGasto(id_usuario, fecha1, fecha2);
        }
    }

    //Obtiene todos los gastos que esten entre las fechas elegidas y el precio este entre las 2 cantidades elegidad (incluidas)
    public ArrayList<Gasto> obtenerGasto(String id_usuario, String fecha1, String fecha2, double c1, double c2) {
        ArrayList<Gasto> gastos =this.obtenerGasto(id_usuario, fecha1, fecha2);
        ArrayList<Gasto> gastosFiltrado=new ArrayList<Gasto>();
        if(c1>=0 && c2>=0){
            double cmenor, cmayor;
            if (c1<=c2){
                cmenor=c1;
                cmayor=c2;
            }else{
                cmenor=c2;
                cmayor=c1;
            }


            for (Gasto g : gastos){
                if (cmenor<=g.getCantidad() && g.getCantidad()<=cmayor){
                    gastosFiltrado.add(g);
                }
            }
        }else{
            gastosFiltrado=gastos;
        }
        return gastosFiltrado;
    }

    //Obtiene los gastos que esten entre las fechas elegidas, tengan el concepto elegido y el precio este entre las 2 cantidades elegidad (incluidas)
    public ArrayList<Gasto> obtenerGasto(String id_usuario, String fecha1, String fecha2, String concepto, double c1, double c2) {
        ArrayList<Gasto> gastos =this.obtenerGasto(id_usuario, fecha1, fecha2, concepto);
        ArrayList<Gasto> gastosFiltrado=new ArrayList<Gasto>();
        if(c1>=0 && c2>=0){
            double cmenor, cmayor;
            if (c1<=c2){
                cmenor=c1;
                cmayor=c2;
            }else{
                cmenor=c2;
                cmayor=c1;
            }


            for (Gasto g : gastos){
                if (cmenor<=g.getCantidad() && g.getCantidad()<=cmayor){
                    gastosFiltrado.add(g);
                }
            }
        }else{
            gastosFiltrado=gastos;
        }
        return gastosFiltrado;
    }

    //Obtiene la suma de la cantidad de todos los gastos
    public double obtenerSuma(String id_usuario) {
        double suma=0;

        String sentenciaSql = "SELECT *" +
                "" + " FROM " + NOMBRE_TABLA_GASTOS +
                " WHERE id_usario LIKE '"+id_usuario+"'";
        Cursor c=null;
        try {
            c = bd.rawQuery(sentenciaSql, null);
        }
        catch (Exception e){
            return 0;
        }
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                int id = c.getInt(c.getColumnIndexOrThrow("_id"));
                String cantidad = c.getString(c.getColumnIndexOrThrow("cantidad"));
                suma+=Double.parseDouble(cantidad);
            } while (c.moveToNext());
        }
        c.close();
        return suma;
    }

    //Obtiene la suma de la cantidad de todos los gastos del año elegido
    public double obtenerSuma(String id_usuario, String año) {
        double suma=0;

        ArrayList<Gasto> gastos =this.obtenerGasto(id_usuario);
        for (Gasto g : gastos){
            if (g.getAño().equals(año)){
                suma+=g.getCantidad();
            }
        }
        return suma;
    }

    //Obtiene la suma de la cantidad de todos los gastos que esten entre las fechas elegidas
    public double obtenerSuma(String id_usuario, String fecha1, String fecha2) {
        double suma=0;

        ArrayList<Gasto> gastos =this.obtenerGasto(id_usuario);
        for (Gasto g : gastos){
            if (g.estaEntre(fecha1,fecha2)){
                suma+=g.getCantidad();
            }
        }
        return suma;
    }

    //Obtiene la suma de la cantidad de todos los gastos que esten entre las fechas elegidas y tengan el concepto elegido
    public double obtenerSuma(String id_usuario, String fecha1, String fecha2, String concepto) {
        try{
            double suma=0;

            ArrayList<Gasto> gastos =this.obtenerGasto(id_usuario);
            for (Gasto g : gastos){
                if (g.estaEntre(fecha1,fecha2) && g.getConcepto().equals(concepto)){
                    suma+=g.getCantidad();
                }
            }
            return suma;
        } catch (Exception e) {
            return obtenerSuma(id_usuario, fecha1, fecha2);
        }

    }
*/
//}

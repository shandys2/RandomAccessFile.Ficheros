package com.journaldev.files;

public class RegistroPersona {

    String nombre,dni,edad;
    String nombreF,dniF,edadF,registro;
    int posRegistro;

    RegistroPersona(String nombre,String dni,String edad){
         this.nombre=nombre;
         this.dni=dni;
         this.edad=edad;
         this.nombreF = String.format("%-10s",nombre);
         this.dniF = String.format("%-10s",dni);
         this.edadF = String.format("%-2s",edad);
         registro = nombreF+dniF+edadF;
    }

     public String getRegistro(){
        return registro;
     }
     public int getPosRegistro(){
        return posRegistro;
     }
}

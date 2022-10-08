package com.journaldev.files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionPersonas {

    RandomAccessFile file;
    List<RegistroPersona> registros;
    static int tamFila=24;
    boolean esFila=true;
    static String lectura;
    public static int posFila=1;
    static int numFila=0;
    static String filePath;
    Scanner sc= new Scanner(System.in);



    GestionPersonas(String filePath) throws FileNotFoundException {
         this.filePath = filePath;
         this.file = new RandomAccessFile(filePath, "rw");
         registros=new ArrayList<>();
    }

    public int getFila(){
         numFila= numFila+24;
         return  numFila;

    }

    public byte[] leerCaracteres( int seek, int chars) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "r");
        file.seek(seek);
        byte[] bytes = new byte[chars];
        file.read(bytes);
        file.close();
        return bytes;
    }

    public void cargarFichero() throws IOException {

        RandomAccessFile file = new RandomAccessFile(filePath, "r");
        lectura=new String(leerCaracteres( 0, tamFila));
        String sinEspacios= lectura.replaceAll(" +"," ");
        String[]aux=sinEspacios.split(" ");
        RegistroPersona p =new RegistroPersona(aux[0],aux[1],aux[2]);
        registros.add(p);

        while(true){

            lectura=new String(leerCaracteres(getFila(), tamFila));

            if(lectura.contains(" ")){
                sinEspacios= lectura.replaceAll(" +"," ");
                aux=sinEspacios.split(" ");
                p =new RegistroPersona(aux[0],aux[1],aux[2]);
                registros.add(p);
            }else{
                break;
            }
        }
        for (RegistroPersona registro: registros) {
            System.out.println(registro.registro);
        }
    }


    public void addPersona(){
       System.out.println("Introduce el nombre");
        String nombre =sc.nextLine();
        System.out.println("Introduce el dni");
        String dni =sc.nextLine();
        System.out.println("Introduce la edad");
        String edad =sc.nextLine();

        String nombreF=String.format("%-10s",nombre);
        String dniF=String.format("%-10s",dni);
        String edadF=String.format("%-2s",edad);
        String registro=nombreF+dniF+edadF;

        String sinEspacios= lectura.replaceAll(" +"," ");
        String [] aux=sinEspacios.split(" ");
        RegistroPersona p =new RegistroPersona(aux[0],aux[1],aux[2]);
        registros.add(p);

    }



    public void imprimirFichero() throws IOException {

        lectura=new String(leerCaracteres( 0, tamFila));
        System.out.println(posFila + "-" + lectura);
        posFila++;
        while(true){
            lectura=new String(leerCaracteres( getFila(), tamFila));
            if(lectura.contains(" ")){
                System.out.println(posFila + "-" + lectura);
                posFila++;
            }else{
                break;
            }
        }
    }

    public  void writeData(String filePath, String data, int seek) throws IOException {
        this.file = new RandomAccessFile(filePath, "rw");
        file.seek(seek);
        file.write(data.getBytes());
        file.close();
    }

}

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
    List<RegistroPersona> registrosAux;
    static int tamFila=23;
    boolean esFila=true;
    static String lectura;
    public static int posFila=1;
    static int numFila=1;
    static String filePath;
    static String pathAux;
    Scanner sc= new Scanner(System.in);
    private int nextFila=-23;


    GestionPersonas(String filePath , String pathAux) throws FileNotFoundException {
         this.filePath = filePath;
         this.pathAux=pathAux;
         this.file = new RandomAccessFile(filePath, "rw");
         registros=new ArrayList<>();
         registrosAux=new ArrayList<>();
    }

    public int getFila(){
         numFila= numFila+23;
         return  numFila;
    }
    public int getNextFila(){
        nextFila=nextFila+23;
        return  nextFila;
    }


    public byte[] leerCaracteres( int seek, int chars ,String path) throws IOException {
        RandomAccessFile file = new RandomAccessFile(path, "r");
        file.seek(seek);
        byte[] bytes = new byte[chars];
        file.read(bytes);
        file.close();
        return bytes;
    }

    public void cargarFichero() throws IOException {

       // RandomAccessFile file = new RandomAccessFile(filePath, "r");

        lectura=new String(leerCaracteres( 0, tamFila ,filePath));
        String sinEspacios= lectura.replaceAll(" +"," ");
        String[]aux=sinEspacios.split(" ");
        RegistroPersona p =new RegistroPersona(aux[0],aux[1],aux[2],posFila);
        registros.add(p);
        posFila++;


        while(true){
            lectura=new String(leerCaracteres(getFila(), tamFila,filePath));

            if(lectura.contains(" ")){
                sinEspacios= lectura.replaceAll(" +"," ");
                aux=sinEspacios.split(" ");
                p =new RegistroPersona(aux[0],aux[1],aux[2],posFila);
                registros.add(p);
                posFila++;
            }else{
                break;
            }
        }
        for (RegistroPersona registro: registros) {
            System.out.println( registro.posRegistro+"-"+registro.registro);
        }
    }

    public void cargarFicheroOriginal() throws IOException {

        nextFila=-23;
        numFila=1;

        String sinEspacios;
        String[]aux;
        RegistroPersona p ;

        while((lectura=new String(leerCaracteres(getNextFila(),tamFila,filePath))).contains(" ")){

            sinEspacios= lectura.replaceAll(" +"," ");
            aux=sinEspacios.split(" ");
            p =new RegistroPersona(aux[0],aux[1],aux[2],numFila);
            registros.add(p);
            numFila++;
        }

        for (RegistroPersona registro: registros) {
            System.out.println( "ORIGINAL "+registro.posRegistro+"-"+registro.registro);
        }
    }

    public void cargarFicheroAux() throws IOException {

        nextFila=-23;
        numFila=1;

        String sinEspacios;
        String[]aux;
        RegistroPersona p ;

        while((lectura=new String(leerCaracteres(getNextFila(),tamFila,pathAux))).contains(" ")){

            sinEspacios= lectura.replaceAll(" +"," ");
            aux=sinEspacios.split(" ");
            p =new RegistroPersona(aux[0],aux[1],aux[2],numFila);
            registrosAux.add(p);
            numFila++;
        }

        for (RegistroPersona registro: registrosAux) {
            System.out.println( "AUX "+registro.posRegistro+"-"+registro.registro);
        }
    }

    public void buscarPersona(){
        System.out.println("Introduce el dni de la persona ");
        String dni= sc.nextLine();
        String dniF=String.format("%-10s",dni);
        for (RegistroPersona r: registros) {
            if(r.dniF.equals(dniF)){
                System.out.println("Encontrado -> "+ r.registro);
            }

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

        RegistroPersona p =new RegistroPersona(nombreF,dniF,edadF,numFila);
        registros.add(p);
        numFila++;
        System.out.println("Registro  añadido correctamente ->" + p.posRegistro+"-"+registro );

    }


    public void guardarEnFicheroAuxiliar() throws IOException {
         for (RegistroPersona r: registros) {
             writeData(pathAux,r.registro,(r.posRegistro * tamFila));
         }
    }

    public void guardarEnFicheroDefinitivo() throws IOException {

        for (RegistroPersona r: registrosAux) {
            writeData(filePath,r.registro,(r.posRegistro * tamFila));
        }

    }


    public void imprimirFichero() throws IOException {

        for (RegistroPersona registro: registros) {
            System.out.println(registro.posRegistro +"-"+registro.registro);
        }
    }

    public  void writeData(String filePath, String data, int seek) throws IOException {
        this.file = new RandomAccessFile(filePath, "rw");
        file.seek(seek);
        file.write(data.getBytes());
        file.close();
    }



}

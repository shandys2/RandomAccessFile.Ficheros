package com.journaldev.files;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int numFila=0;
    List<RegistroPersona> registros;

    public static void main(String[] args) {


        Scanner sc= new Scanner(System.in);
        try {
            String filePath = "/home/shandys/Documentos/usuariosbis";
            String pathAux ="/home/shandys/Documentos/auxUsuarios.txt";

            GestionPersonas gp= new GestionPersonas(filePath, pathAux);

            int opt;

            do {
               System.out.println("INTRODUCE LA ACCION A REALIZAR");
               System.out.println("1 - Cargar Fichero -> usuarios.txt");
               System.out.println("2 - Añadir Registro");
               System.out.println("3 - Imprimir Fichero");
               System.out.println("4 - Guardar copia en fichero auxiliar -> auxUsuarios.txt");
               System.out.println("5 - Cargar el fichero auxiliar en el programa");
               System.out.println("6 - Volcado definitivo en archivo origen -> usuarios.txt");
               System.out.println("7 - Buscar registro");
               System.out.println("0 - Salir");
               opt = sc.nextInt();

               switch (opt){
                   case 1 : gp.cargarFicheroOriginal();break;
                   case 2 : gp.addPersona();break;
                   case 3 : gp.imprimirFichero();break;
                   case 4 : gp.guardarEnFicheroAuxiliar();break;
                   case 5 : gp.cargarFicheroAux();break;
                   case 6:  gp.guardarEnFicheroDefinitivo();break;
                   case 7:  gp.buscarPersona();break;
                   case 0 : break;
               }

            }while(opt!=0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
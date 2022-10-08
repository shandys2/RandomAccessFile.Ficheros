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
            String filePath = "C:\\Users\\danie\\OneDrive\\Documentos\\usuarios.txt";
            GestionPersonas gp= new GestionPersonas(filePath);

                System.out.println("INTRODUCE LA ACCION A REALIZAR");

                 gp.cargarFichero();




            // System.out.println(gp.numFila+"-"+registro);

            //writeData(filePath, "Data", 5);
            //now file content is "ABCDEData"

            //appendData(filePath, "pankaj");
            //now file content is "ABCDEDatapankaj"
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
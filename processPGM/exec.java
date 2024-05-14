package com.mycompany.dam;

import com.mycompany.dam.tratarPGM;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Rodrigo Andrés y Izan Calvo
 */
public class exec {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String resp;
        tratarPGM pgm = null;
        String nFile = "";
        boolean existe = false;
        File f;
        do {
            System.out.println("Introduce la ruta del fichero: ");
            nFile = sc.nextLine();
            try {
                pgm = new tratarPGM(nFile);
                existe = true;
            } catch (NumberFormatException e) {
                System.out.println("Formato del fichero incorrecto");
            } catch (FileNotFoundException e) {
                System.out.println("Fichero no encontrado");
            } catch (Exception e) {
                System.out.println("Se ha producido un error inesperado");
            }
        } while (!existe);
        
        do {
            System.out.println("¿Que quieres hacer?");
            System.out.println("1) filtro girar izquierda");
            System.out.println("2) filtro girar derecha");
            System.out.println("3) filtro flip horizontal");
            System.out.println("4) filtro flip vertical");
            System.out.println("5) filtro obtener negativo");
            System.out.println("6) filtro obtener filtro caja");
            System.out.println("G) Guardar");
            System.out.println("S) Salir");
            System.out.print("Selección: ");
            resp = sc.nextLine();
            if (!resp.equalsIgnoreCase("s")) {
                if (resp.equals("1")) {
                    pgm.girarIzquierda();
                } else if (resp.equals("2")) {
                    pgm.girarDerecha();
                } else if (resp.equals("3")) {
                    pgm.flipHorizontal();
                } else if (resp.equals("4")) {
                    pgm.flipVertical();
                } else if (resp.equals("5")) {
                    pgm.obtenerNegativo();
                } else if (resp.equals("6")) {
                    pgm.filtroCaja();
                } else if (resp.equalsIgnoreCase("G")) {

                    try {
                        System.out.println("Introduce la ruta con el nombre del fichero: ");
                        nFile = sc.nextLine();
                        pgm.guardarPGM(nFile);
                    } catch (Exception ex) {
                        System.out.println("Se ha producido un error al guardar");
                    }
                }else {
                    System.out.println("\"" + resp + "\"" + ", no es una opción valida");
                    System.out.println("---------------------------------------------");
                }

            }
        } while (!resp.equalsIgnoreCase("s"));
        System.out.println("¡Hasta la proxima!");
    }
    
}

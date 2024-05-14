package com.mycompany.dam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author Rodrigo Andrés y Izan Calvo
 */
public class tratarPGM {

    private int ancho;
    private int alto;
    private int blancoAbsoluto;
    private double[][] pixeles;

    public tratarPGM(String nFile) throws Exception {
        File f = new File(nFile);
        try (Scanner scFile = new Scanner(f)) {
            scFile.nextLine();
            scFile.nextLine();
            this.ancho = scFile.nextInt();
            this.alto = scFile.nextInt();
            scFile.nextLine();
            this.blancoAbsoluto = scFile.nextInt();
            this.pixeles = new double[this.alto][this.ancho];
            for (int i = 0; i < this.pixeles.length; i++) {
                for (int j = 0; j < this.pixeles[i].length; j++) {
                    this.pixeles[i][j] = scFile.nextInt() * 255 / this.blancoAbsoluto;
                }
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public void girarIzquierda() {
        try {

            double[][] pxTmp = new double[this.ancho][this.alto];

            for (int i = 0; i < pxTmp.length; i++) {
                for (int j = 0; j < pxTmp[i].length; j++) {
                    pxTmp[i][j] = this.pixeles[j][this.ancho - 1 - i];

                }
            }

            this.pixeles = pxTmp;
            this.ancho = this.pixeles[0].length;
            this.alto = this.pixeles.length;

        } catch (Exception e) {
            System.out.println("Se ha producido un error al girar a la izquierda");
        }
        System.out.println("Giro a la izquierda aplicado");
        System.out.println("----------------------------");

    }

    public void girarDerecha() {
        try {

            double[][] pxTmp = new double[this.ancho][this.alto];

            for (int i = 0; i < pxTmp.length; i++) {
                for (int j = 0; j < pxTmp[i].length; j++) {
                    pxTmp[i][j] = this.pixeles[pxTmp[i].length - 1 - j][i];

                }
            }

            this.pixeles = pxTmp;
            this.ancho = this.pixeles[0].length;
            this.alto = this.pixeles.length;

        } catch (Exception e) {
            System.out.println("Se ha producido un error al girar a la derecha");
        }
        System.out.println("Giro a la derecha aplicado");
        System.out.println("----------------------------");
    }

    public void flipHorizontal() {
        try {

            double[][] pxTmp = new double[this.alto][this.ancho];
            int contI = this.alto - 1;

            for (int i = 0; i < this.pixeles.length; i++) {
                for (int j = 0; j < this.pixeles[i].length; j++) {
                    pxTmp[contI][j] = this.pixeles[i][j];

                }
                contI--;
            }
            this.pixeles = pxTmp;

        } catch (Exception e) {
            System.out.println("Se ha producido un error al ejecutar flip horizontal");
        }
        System.out.println("Flip horizontal aplicado");
        System.out.println("----------------------------");

    }

    public void flipVertical() {
        try {

            double[][] pxTmp = new double[this.alto][this.ancho];
            int contJ;

            for (int i = 0; i < this.pixeles.length; i++) {
                contJ = this.ancho - 1;
                for (int j = 0; j < this.pixeles[i].length; j++) {
                    pxTmp[i][contJ] = this.pixeles[i][j];
                    contJ--;
                }
            }
            this.pixeles = pxTmp;

        } catch (Exception e) {
            System.out.println("Se ha producido un error al ejecutar flip vertical");
            e.printStackTrace();
        }
        System.out.println("Flip vertical aplicado");
        System.out.println("----------------------------");
    }

    public void obtenerNegativo() {
        try {
            for (int i = 0; i < this.pixeles.length; i++) {
                for (int j = 0; j < this.pixeles[i].length; j++) {
                    this.pixeles[i][j] = 255 - this.pixeles[i][j];
                }
            }

        } catch (Exception e) {
            System.out.println("Se ha producido un error");
        }
        System.out.println("Negativo aplicado");
        System.out.println("----------------------------");
    }

    public void filtroCaja() {
        double[][] pxTmp = new double[this.alto][this.ancho];
        double acum;
        int div;
        double pixelProcesado;

        try {
            for (int i = 0; i < this.pixeles.length; i++) {
                for (int j = 0; j < this.pixeles[i].length; j++) {
                    acum = 0;
                    div = 0;
                    //fila superior
                    if (!(i - 1 < 0 || j - 1 < 0)) {
                        acum += this.pixeles[i - 1][j - 1];
                        div++;
                    }
                    if (!(i - 1 < 0 || j < 0)) {
                        acum += this.pixeles[i - 1][j];
                        div++;
                    }
                    if (!(i - 1 < 0 || j + 1 > this.pixeles[i].length - 1)) {
                        acum += this.pixeles[i - 1][j + 1];
                        div++;
                    }
                    //fila media
                    if (!(i < 0 || j - 1 < 0)) {
                        acum += this.pixeles[i][j - 1];
                        div++;
                    }
                    if (!(i < 0 || j < 0)) {
                        acum += this.pixeles[i][j];
                        div++;
                    }
                    if (!(i < 0 || j + 1 > this.pixeles[i].length - 1)) {
                        acum += this.pixeles[i][j + 1];
                        div++;
                    }
                    //fila inferior
                    if (!(i + 1 > this.pixeles.length - 1 || j - 1 < 0)) {
                        acum += this.pixeles[i + 1][j - 1];
                        div++;
                    }
                    if (!(i + 1 > this.pixeles.length - 1 || j < 0)) {
                        acum += this.pixeles[i + 1][j];
                        div++;
                    }
                    if (!(i + 1 > this.pixeles.length - 1 || j + 1 > this.pixeles[i].length - 1)) {
                        acum += this.pixeles[i + 1][j + 1];
                        div++;
                    }
                    pixelProcesado = acum / div;
                    pxTmp[i][j] = pixelProcesado;
                }
            }
            this.pixeles = pxTmp;

        } catch (Exception e) {
            System.out.println("Se ha producido un error al aplicar el filtro caja");
            e.printStackTrace();
        }
        System.out.println("Filtro caja aplicado");
        System.out.println("----------------------------");

    }

    public void guardarPGM(String newName) throws Exception {
        File nf = new File(newName);

        try (FileWriter fw = new FileWriter(nf)) {
            int redondeado;

            fw.write("P2\n");
            fw.write("# " + newName + "\n");
            fw.write(this.pixeles[0].length + " " + this.pixeles.length + "\n");
            fw.write((int) this.blancoAbsoluto + "\n");

            for (int i = 0; i < this.pixeles.length; i++) {
                for (int j = 0; j < this.pixeles[i].length; j++) {
                    redondeado = (int) Math.round(this.pixeles[i][j] * this.blancoAbsoluto / 255);
                    fw.write(redondeado + "  ");
                }
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Se ha producido un error al guardar");
        }
        System.out.println("Imagen guardada");
        System.out.println("----------------------------");
    }
}

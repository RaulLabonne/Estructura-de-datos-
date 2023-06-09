package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.ArbolAVL;
import mx.unam.ciencias.edd.ArbolBinarioCompleto;
import mx.unam.ciencias.edd.ArbolBinarioOrdenado;
import mx.unam.ciencias.edd.ArbolRojinegro;
import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto2.graphic.GraficadorAVL;
import mx.unam.ciencias.edd.proyecto2.graphic.GraficadorArbol;
import mx.unam.ciencias.edd.proyecto2.graphic.GraficadorCola;
import mx.unam.ciencias.edd.proyecto2.graphic.GraficadorGrafica;
//import mx.unam.ciencias.edd.proyecto2.graphic.GraficadorGrafica;
import mx.unam.ciencias.edd.proyecto2.graphic.GraficadorLista;
import mx.unam.ciencias.edd.proyecto2.graphic.GraficadorPila;

/* 
 * Clase que dada una colección, da el codigo svg para su graficación.
 */
public class ColeccionGrafica {
    
    /* Numero de elementos */
    int elementos;
    /* Lista con los elementos */
    Lista<Integer> lista;
    /* El tipo de colección */
    TipoColeccion coleccion;

    /* Constructor de la clase */
    public ColeccionGrafica(Lista<Integer> lista, TipoColeccion coleccion){
        this.lista = lista;
        this.coleccion = coleccion;
        elementos = lista.getElementos();
    }

    /* 
     * Metodo que dado el tipo de coleccion da el codigo svg en una cadena para 
     * poder ser guardada en un archivo de tipo .svg.
     * Si el tipo de colección es invalido, terminalos el programa.
     */
    public String codigoSVG(){
        String s = "";
        switch (coleccion) {
            case LISTA:
                GraficadorLista gLista = new GraficadorLista(elementos, lista);
                s = gLista.codigoSVG();
                break;
            case COLA:
                GraficadorCola gCola = new GraficadorCola(elementos, lista.reversa());
                s = gCola.codigoSVG();
                break;
            case PILA:
                GraficadorPila gPila = new GraficadorPila(elementos, lista.reversa());
                s = gPila.codigoSVG();
                break;
            case ARBOLBINARIO_ORDENADO:
                ArbolBinarioOrdenado<Integer> aOrdenado = new ArbolBinarioOrdenado<>(lista);
                GraficadorArbol gAOrdenado = new GraficadorArbol(aOrdenado, elementos);
                s = gAOrdenado.codigoSVG();
                break;
            case ARBOLBINARIO_COMPLETO:
                ArbolBinarioCompleto<Integer> aCompleto = new ArbolBinarioCompleto<>(lista);
                GraficadorArbol gACompleto = new GraficadorArbol(aCompleto, elementos);
                s = gACompleto.codigoSVG();
                break;
            case ARBOLBINARIO_ROJINEGRO:
                ArbolRojinegro<Integer> rojinegro = new ArbolRojinegro<>(lista);
                GraficadorArbol gRojinegro = new GraficadorArbol(rojinegro, elementos);
                s = gRojinegro.codigoSVG();
                break;
            case ARBOLBINARIO_AVL:
                ArbolAVL<Integer> avl = new ArbolAVL<>(lista);
                GraficadorAVL gAVL = new GraficadorAVL(avl, elementos);
                s = gAVL.codigoSVG();
                break;
            case GRAFICA:
                Grafica<Integer> grafica = new Grafica<>();
                if (elementos % 2 != 0)
                    usoGrafica();
                GraficaValida valido = new GraficaValida(lista.copia(), elementos);
                grafica = valido.getGrafica();
                GraficadorGrafica gGrafica = new GraficadorGrafica(grafica);
                s = gGrafica.codigoSVG();
                break;
            // Si el tipo de colección es invalido.
            default:
                uso();
        }
        return s;
    }

    /* Cierra el programa para el caso default o invalido  */
    private void uso(){
        System.out.println("Tipo de colección no disponible, favor de verificar que su tipo de colección este bien escrito o que se encuentre en la siguiente lista.\n" +
        "Tipos de datos disponibles:\n" + "\tLista\n \tCola\n \tPila\n \tArbolBinarioOrdenado\n \tArbolBinarioCompleto\n \tArbolRojinegro\n \tArbolAVL\n");
        System.exit(1);
    }

    /* Cierra el programa si en el caso Grafica la cantidad de elementos es impar */
    private void usoGrafica(){
        System.out.println("Para poder generar una Grafica, la cantidad de elementos debe ser par: \n" + "Cantidad de elementos: " + elementos);
        System.exit(1);
    }
}

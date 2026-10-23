/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import modelo.Pasajero;
import persistencia.PasajeroData;

public class Main {
    public static void main(String[] args) {
        PasajeroData data = new PasajeroData();

        System.out.println("=== INTEGRANTES DEL GRUPO ===");
        System.out.println("1. Juan Gomez - DNI 30111222");
        System.out.println("2. Maria Lopez - DNI 30222333");
        System.out.println("3. (agregar los tuyos)");
        System.out.println();

        // ALTA
        Pasajero p1 = new Pasajero("30111222", "Juan", "Gomez",
                                   "jgomez@gmail.com", "155555111", true);
        data.insertar(p1);

        // BUSCAR
        Pasajero encontrado = data.buscar(p1.getIdPasajero());
        System.out.println("Buscado: " + encontrado);

        // LISTAR
        System.out.println("\n=== LISTADO DE PASAJEROS ===");
        for (Pasajero p : data.listar()) {
            System.out.println(p);
        }

        // MODIFICAR (opcional)
        if (encontrado != null) {
            encontrado.setTelefono("155555999");
            data.actualizar(encontrado);
        }

        // BAJA LÓGICA (opcional)
        data.bajaLogica(p1.getIdPasajero());

        // ALTA LÓGICA (opcional)
        data.altaLogica(p1.getIdPasajero());

        // BORRAR (opcional - cuidado, borra el registro)
        // data.borrar(p1.getIdPasajero());
    }
}

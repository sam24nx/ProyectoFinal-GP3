package vista;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import modelo.Colectivo;
import modelo.Lugar;
import modelo.Pasajero;
import modelo.Viaje;
import persistencia.Asientodata;
import persistencia.Colectivodata;
import persistencia.PasajeroData;
import persistencia.ViajeData;

public class Main {
    public static void main(String[] args) {
        Colectivodata colData = new Colectivodata();
        PasajeroData pasData = new PasajeroData();
        Asientodata asiData = new Asientodata();
        ViajeData viaData = new ViajeData();

//colectivos
        Colectivo[] colectivos = {
            new Colectivo("AA101BB", "Mercedes-Benz", "O500 RSD", 44, "Carlos Perez", true),
            new Colectivo("AB202CC", "Scania", "K410", 48, "Roberto Gomez", true),
            new Colectivo("AC303DD", "Volvo", "B450R", 40, "Miguel Torres", true),
            new Colectivo("AD404EE", "Mercedes-Benz", "O500 RS", 46, "Luis Fernandez", true),
            new Colectivo("AE505FF", "Scania", "K360", 50, "Jorge Diaz", true),
            new Colectivo("AF606GG", "Volvo", "B380R", 42, "Hector Ruiz", true),
            new Colectivo("AG707HH", "Iveco", "Bus 170S28", 38, "Daniel Sosa", true),
            new Colectivo("AH808II", "Mercedes-Benz", "Marcopolo G7", 44, "Raul Castro", true),
            new Colectivo("AI909JJ", "Scania", "Irizar i6", 48, "Oscar Morales", true),
            new Colectivo("AJ010KK", "Volvo", "Comil Campione", 40, "Pedro Silva", true)
        };
        for (Colectivo c : colectivos) {
            colData.insertarColectivo(c);
        }

        List<Colectivo> listaColectivos = colData.listar();

//pasajeros
        Pasajero[] pasajeros = {
            new Pasajero("30111222", "Juan", "Gomez", "jgomez@gmail.com", "2664111222", true),
            new Pasajero("31222333", "Maria", "Lopez", "mlopez@gmail.com", "2664222333", true),
            new Pasajero("48293678", "Facundo", "Maldonado", "fmaldonado@gmail.com", "2664333444", true),
            new Pasajero("45674835", "Sam", "Olguin", "solguin@gmail.com", "2664444555", true),
            new Pasajero("58284932", "Luis Roman", "Manzano", "lmanzano@gmail.com", "2664555666", true),
            new Pasajero("28999888", "Ana", "Martinez", "amartinez@gmail.com", "2664666777", true),
            new Pasajero("33444555", "Diego", "Rodriguez", "drodriguez@gmail.com", "2664777888", true),
            new Pasajero("35666777", "Laura", "Sanchez", "lsanchez@gmail.com", "2664888999", true),
            new Pasajero("38999000", "Martin", "Ramirez", "mramirez@gmail.com", "2664999000", true),
            new Pasajero("40123456", "Sofia", "Acosta", "sacosta@gmail.com", "2664123456", true)
        };
        for (Pasajero p : pasajeros) {
            pasData.insertar(p);
        }

//asientos
        if (!listaColectivos.isEmpty()) {
            for (int i = 0; i < 10; i++) {
                Colectivo colAsignado = listaColectivos.get(i % listaColectivos.size());
                int numeroAsiento = (i / listaColectivos.size()) + 1;
                asiData.insertar(new Lugar(colAsignado, numeroAsiento, true));
            }
        }

//rutas
        if (!listaColectivos.isEmpty()) {
            LocalDate manana = LocalDate.now().plusDays(1);
            Viaje[] viajes = {
                new Viaje("San Luis", "Mendoza", manana, LocalTime.of(8, 0), LocalTime.of(11, 30), listaColectivos.get(0 % listaColectivos.size()), 18500.0, true),
                new Viaje("San Luis", "Cordoba", manana, LocalTime.of(9, 15), LocalTime.of(14, 45), listaColectivos.get(1 % listaColectivos.size()), 24000.0, true),
                new Viaje("San Luis", "Buenos Aires", manana, LocalTime.of(20, 0), LocalTime.of(6, 30), listaColectivos.get(2 % listaColectivos.size()), 52000.0, true),
                new Viaje("Villa Mercedes", "San Luis", manana.plusDays(1), LocalTime.of(7, 30), LocalTime.of(8, 45), listaColectivos.get(3 % listaColectivos.size()), 7500.0, true),
                new Viaje("Merlo", "San Luis", manana.plusDays(1), LocalTime.of(10, 0), LocalTime.of(13, 0), listaColectivos.get(4 % listaColectivos.size()), 12000.0, true),
                new Viaje("Mendoza", "San Luis", manana.plusDays(2), LocalTime.of(15, 0), LocalTime.of(18, 30), listaColectivos.get(5 % listaColectivos.size()), 18500.0, true),
                new Viaje("Cordoba", "San Luis", manana.plusDays(2), LocalTime.of(16, 30), LocalTime.of(22, 0), listaColectivos.get(6 % listaColectivos.size()), 24000.0, true),
                new Viaje("San Luis", "San Juan", manana.plusDays(3), LocalTime.of(6, 45), LocalTime.of(11, 15), listaColectivos.get(7 % listaColectivos.size()), 21000.0, true),
                new Viaje("San Luis", "Rosario", manana.plusDays(3), LocalTime.of(21, 30), LocalTime.of(6, 0), listaColectivos.get(8 % listaColectivos.size()), 46000.0, true),
                new Viaje("San Luis", "La Punta", manana.plusDays(4), LocalTime.of(12, 0), LocalTime.of(12, 35), listaColectivos.get(9 % listaColectivos.size()), 2500.0, true)
            };
            for (Viaje v : viajes) {
                viaData.guardarViaje(v);
            }
        }


    }
}
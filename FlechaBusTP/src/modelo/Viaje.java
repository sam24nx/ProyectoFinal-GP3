/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author facun
 */
public class Viaje {
    
    
    private int idViaje;
    private String origen;
    private String destino;
    private LocalDate fecha;
    private LocalTime horaSalida;
    private LocalTime horaLlegada;
    private Colectivo colectivo;
    private double precio;
    private boolean estado;
    
    public Viaje(){}
    
    
public Viaje(int idViaje, String origen, String destino, LocalDate fecha, LocalTime horaSalida, LocalTime horaLlegada, Colectivo colectivo, double precio, boolean estado) {
        this.idViaje = idViaje;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.colectivo = colectivo;
        this.precio = precio;
        this.estado = estado;
    }
    
    public Viaje(String origen, String destino, LocalDate fecha, LocalTime horaSalida, LocalTime horaLlegada, Colectivo colectivo, double precio, boolean estado) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.colectivo = colectivo;
        this.precio = precio;
        this.estado = estado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    
    
    
    
    public int getIdViaje() {
        return idViaje;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    public Colectivo getColectivo() {
        return colectivo;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setHoraLlegada(LocalTime horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public void setColectivo(Colectivo colectivo) {
        this.colectivo = colectivo;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

@Override
public String toString() {
    return origen + " -> " + destino + " (" + fecha + ")";
}


    
    
    
    
    
    
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author facun
 */
public class Colectivo {
    
    private int idColectivo;
    private String matricula;
    private String marca;
    private String modelo;
    private int capacidad;
    private String chofer;
    private boolean estado;
    
    public Colectivo(){
    }
    
    public Colectivo(int idColectivo,String matricula,String marca,String modelo,int capacidad,String chofer,boolean estado) {
        this.idColectivo=idColectivo;
        this.matricula=matricula;
        this.marca=marca;
        this.modelo=modelo;
        this.capacidad=capacidad;
        this.chofer=chofer;
        this.estado=estado;}
    
    
    public Colectivo(String matricula, String marca, String modelo, int capacidad, String chofer, boolean estado) {
        this.matricula=matricula;
        this.marca=marca;
        this.modelo=modelo;
        this.capacidad=capacidad;
        this.chofer=chofer;
        this.estado=estado;
    }
    

    public int getIdColectivo() {
        return idColectivo;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getChofer() {
        return chofer;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setIdColectivo(int idColectivo) {
        this.idColectivo = idColectivo;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setChofer(String chofer) {
        this.chofer = chofer;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Colectivo{" + "idColectivo=" + idColectivo + ", matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", capacidad=" + capacidad + ", chofer=" + chofer + ", estado=" + estado + '}';
    }
    
    
    
    
    
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author facun
 */
public class Lugar {
    
    private int idAsiento;
    private Colectivo colectivo;
    private int numeroAsiento;
    private boolean estado;
    
    
    public Lugar(){}
    
    public Lugar(int idAsiento,Colectivo colectivo,int numeroAsiento,boolean estado) {
        this.idAsiento=idAsiento;
        this.colectivo=colectivo;
        this.numeroAsiento=numeroAsiento;
        this.estado=estado;  
}
    
    public Lugar(Colectivo colectivo,int numeroAsiento,boolean estado) {
        this.colectivo=colectivo;
        this.numeroAsiento=numeroAsiento;
        this.estado=estado;
    }

    public int getIdAsiento() {
        return idAsiento;
    }

    public Colectivo getColectivo() {
        return colectivo;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    public void setColectivo(Colectivo colectivo) {
        this.colectivo = colectivo;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Lugar{" + "idAsiento=" + idAsiento + ", colectivo=" + colectivo + ", numeroAsiento=" + numeroAsiento + ", estado=" + estado + '}';
    }
    
    
    
    
    
    
    
}
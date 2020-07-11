package com.example.ejemplowebservice.modeloVO;

public class Medicamento {
    private int id;
    private String nombremedicamento;
    private int cantidad;
    private double precio;
    private String fechavencimiento;

    public Medicamento(){

    }

    public Medicamento(int id, String nombremedicamento, int cantidad, double precio, String fechavencimiento) {
        this.id = id;
        this.nombremedicamento = nombremedicamento;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fechavencimiento = fechavencimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombremedicamento() {
        return nombremedicamento;
    }

    public void setNombremedicamento(String nombremedicamento) {
        this.nombremedicamento = nombremedicamento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(String fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }
}

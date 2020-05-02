package com.example.banco.modelo;

public class Movimiento {

    
    private int idCliente;
    private int idClienteAct;
    private String nrCuenta;
    private String nroCuentaAct;
    private String tipoMovi;
    private Double monto;
    private String color;
    private String nombClienteOtro;
    private String nombCliente;
    private Double montoActualHaceDeposito;
    private Double montoActualReciboDeposito;
    private String tipo_mone;

    public Movimiento(int idCliente, int idClienteAct, String nrCuenta, String nroCuentaAct,
                      String tipoMovi, Double monto, String color, String nombClienteOtro,
                      String nombCliente, String tipomone) {
        this.idCliente = idCliente;
        this.idClienteAct = idClienteAct;
        this.nrCuenta = nrCuenta;
        this.nroCuentaAct = nroCuentaAct;
        this.tipoMovi = tipoMovi;
        this.monto = monto;
        this.color = color;
        this.nombClienteOtro = nombClienteOtro;
        this.nombCliente = nombCliente;
        this.tipo_mone = tipomone;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNrCuenta() {
        return nrCuenta;
    }

    public void setNrCuenta(String nrCuenta) {
        this.nrCuenta = nrCuenta;
    }

    public String getTipoMovi() {
        return tipoMovi;
    }

    public void setTipoMovi(String tipoMovi) {
        this.tipoMovi = tipoMovi;
    }
    public String getTipoMone() {
        return tipo_mone;
    }

    public void setTipoMone(String tipo_mone) {
        this.tipo_mone = tipo_mone;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public int getIdClienteAct() {
        return idClienteAct;
    }

    public void setIdClienteAct(int idClienteAct) {
        this.idClienteAct = idClienteAct;
    }

    public String getNroCuentaAct() {
        return nroCuentaAct;
    }

    public void setNroCuentaAct(String nroCuentaAct) {
        this.nroCuentaAct = nroCuentaAct;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNombClienteOtro() {
        return nombClienteOtro;
    }

    public void setNombClienteOtro(String nombClienteOtro) {
        this.nombClienteOtro = nombClienteOtro;
    }

    public String getNombCliente() {
        return nombCliente;
    }

    public void setNombCliente(String nombCliente) {
        this.nombCliente = nombCliente;
    }

    public Double getMontoActualHaceDeposito() {
        return montoActualHaceDeposito;
    }

    public void setMontoActualHaceDeposito(Double montoActualHaceDeposito) {
        this.montoActualHaceDeposito = montoActualHaceDeposito;
    }

    public Double getMontoActualReciboDeposito() {
        return montoActualReciboDeposito;
    }

    public void setMontoActualReciboDeposito(Double montoActualReciboDeposito) {
        this.montoActualReciboDeposito = montoActualReciboDeposito;
    }
      public String getTipo_mone() {
        return tipo_mone;
    }

    public void setTipo_mone(String tipo_mone) {
        this.tipo_mone = tipo_mone;
    }
}
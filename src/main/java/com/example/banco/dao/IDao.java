package com.example.banco.dao;

import com.example.banco.modelo.Movimiento;
import com.example.banco.modelo.RptaMovimiento;

public interface IDao {
    RptaMovimiento realizarTransferencia(Movimiento movi);
    RptaMovimiento getMovimientos(int idClienteLogeado);
    int iniCliente(int idCliente);
    String getNroCuentaCliente(int idCliente);
    Double getMontoDepositoCliente(int idCliente);
    int getCantDepositosCliente(int idCliente);
    Double getSaldoCliente(int idCliente);
}
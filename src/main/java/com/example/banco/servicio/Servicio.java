package com.example.banco.servicio;

import com.example.banco.dao.Dao;
import com.example.banco.dao.DaoBD;
import com.example.banco.modelo.Movimiento;
import com.example.banco.modelo.RptaMovimiento;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class Servicio {
    
    private final DaoBD dao;
    
    public Servicio(@Qualifier("daobd") DaoBD __dao) {
        this.dao = __dao;
    }
    
    
    public RptaMovimiento realizarTransferencia_PorBD(Movimiento movi) {
        return dao.realizarTransferencia(movi);
    }
    
    public RptaMovimiento realizarTransferencia(Movimiento movi) {
     
        RptaMovimiento rpta = new RptaMovimiento();
        
        if(movi.getMonto() <= 0) {
            rpta.setCodigo_error(1);
            rpta.setMsj_error("El monto tiene que ser un número mayor.");
            return rpta;
        }
        int ini = dao.iniCliente(movi.getIdCliente());
        if(ini == 0) {
            rpta.setCodigo_error(1);
            rpta.setMsj_error("El cliente con el id "+movi.getIdCliente()+" no se encuentra");
            return rpta;
        }
   
        Double deposAct = dao.getMontoDepositoCliente(movi.getIdCliente());
        if(deposAct + movi.getMonto() > 500) {
            rpta.setCodigo_error(1);
            rpta.setMsj_error("El cliente supera la cantidad no deseada, compruebe con un monto menor "+ (500 - deposAct) );
            return rpta;
        }
        int DepositosRe = dao.getCantDepositosCliente(movi.getIdCliente());
        if(DepositosRe + 1 >= 5) {
            rpta.setCodigo_error(1);
            rpta.setMsj_error("El cliente ha llegado al límite de transferencias");
            return rpta;
        }

        ini = dao.iniCliente(movi.getIdClienteAct());
        if(ini == 0) {
            rpta.setCodigo_error(1);
            rpta.setMsj_error("El cliente con el id "+movi.getIdClienteAct()+" no se encuentra");
            return rpta;
        }
        String nroCuenta = dao.getNroCuentaCliente(movi.getIdCliente());
        if(nroCuenta.equals(movi.getNrCuenta())) {
            rpta.setCodigo_error(1);
            rpta.setMsj_error("No se puede depositar");
            return rpta;
        }
       
        double monto_actual = dao.getSaldoCliente(movi.getIdCliente());
        System.err.println("saldo al que depositaste: "+monto_actual);
        if(movi.getMonto() > monto_actual) {
            rpta.setCodigo_error(1);
            rpta.setMsj_error("El cliente no tiene el saldo necesariamente");
            return rpta;
        }
        movi.setMontoActualHaceDeposito(monto_actual);
        
        monto_actual = dao.getSaldoCliente(movi.getIdClienteAct());
        movi.setMontoActualReciboDeposito(monto_actual);
       
        return dao.realizarTransferencia(movi);
    }
    
    public RptaMovimiento getMovimientos(int idClienteLogeado) {
        return dao.getMovimientos(idClienteLogeado);
    }
    
    public String getNroCuentaCliente(int idCliente) {
        return dao.getNroCuentaCliente(idCliente);
    }
}
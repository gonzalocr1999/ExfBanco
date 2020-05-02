package com.example.banco.api;

import com.example.banco.modelo.Movimiento;
import com.example.banco.modelo.RptaMovimiento;
import com.example.banco.servicio.Servicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/banco")
@RestController
public class Controlador {
    
    private final Servicio servicio;
    
    @Autowired
    public Controlador(Servicio __servicio) {
        this.servicio = __servicio;
    }
    
    @RequestMapping("/realizarTransferencia")
    @PostMapping
    public RptaMovimiento realizarTransferencia(@RequestBody Movimiento movi) {
        return servicio.realizarTransferencia_PorBD(movi);
    }
    
    @RequestMapping(value = "/getMovimientos/{idClienteLogin}", method = RequestMethod.GET)
    public RptaMovimiento getMovimientos(@PathVariable("idClienteLogin") int idClienteLogin) {
        return servicio.getMovimientos(idClienteLogin);
    }
    
    @RequestMapping(value = "/getNroCuentaCliente/{idClienteLogin}", method = RequestMethod.GET)
    public String getNroCuentaCliente(@PathVariable("idClienteLogin") int idClienteLogin) {
        return servicio.getNroCuentaCliente(idClienteLogin);
    }
       @RequestMapping(value = "/getlogin/{idClienteLogin}", method = RequestMethod.GET)
    public RptaMovimiento getlogin(@PathVariable("idClienteLogin") int idClienteLogin) {
        return servicio.getMovimientos(idClienteLogin);
    }
 
}
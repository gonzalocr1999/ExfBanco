package com.example.banco.dao;

import com.example.banco.modelo.Movimiento;
import com.example.banco.modelo.RptaMovimiento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("bancodao")
public class Dao implements IDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public RptaMovimiento realizarTransferencia(Movimiento movi) {
        RptaMovimiento rpta = new RptaMovimiento();
        String sql = "insert into movimiento (id_cliente, nro_cuenta, tipo_movi,tipo_mone, monto) VALUES (?, ?, ?, ?,?)";
   
        jdbcTemplate.update(
                sql,
                movi.getIdCliente(),
                movi.getNrCuenta(), 
                movi.getTipoMovi(), 
                movi.getTipoMone(),
                movi.getMonto()
        );
    
        jdbcTemplate.update(
                sql,
                movi.getIdClienteAct(), 
                movi.getNroCuentaAct(), 
                "ING",
                movi.getMonto()
        );

        sql = "update cliente set saldo = ? where id_cliente = ?";
        jdbcTemplate.update(
                sql,
                movi.getMontoActualHaceDeposito() - movi.getMonto(),
                movi.getIdCliente()
        );

        sql = "update cliente set saldo = ? where id_cliente = ?";
        jdbcTemplate.update(
                sql,
                movi.getMontoActualReciboDeposito()+ movi.getMonto(),
                movi.getIdClienteAct()
        );
        //
        rpta.setCodigo_error(0);
        rpta.setMsj_error("El depósito se realizó exitosamente");
        return rpta;
    }

    @Override
    public RptaMovimiento getMovimientos(int idClienteLogin) {
        RptaMovimiento rpta = new RptaMovimiento();
        int existe = iniCliente(idClienteLogin);
        if(existe == 0) {
            rpta.setCodigo_error(1);
            rpta.setMsj_error("El cliente con el ID "+idClienteLogin+" no se encuentra");
            return rpta;
        }
        String sql = "select c1.nomb_cliente as user_logeado,\n" +
                     "       m.tipo_movi,\n" +
                     "       m.monto,\n" +
                     "       c2.nomb_cliente as nomb_cliente_actu,\n" +
                     "       c2.nro_cuenta as nro_cuenta_actu, \n" +
                     "       case when m.tipo_movi = 'GAAR' then 'rojo' else 'verde' and as color\n" +
                     "  from movimiento m,\n" +
                     "       cliente c1,\n" +
                     "       cliente c2\n" +
                     " where m.id_cliente = c1.id_cliente\n" +
                     "   and m.nro_cuenta = c2.nro_cuenta\n" +
                     "   and c1.id_cliente = ?";
        List<Movimiento> lstMovi = jdbcTemplate.query(sql,
                new Object[]{idClienteLogin},
                (rs, rptNum) -> 
                        new Movimiento(0,
                                0,
                                null,
                                rs.getString("nro_cuenta_actu"),
                                rs.getString("tipo_movi"),
                                rs.getDouble("monto"),
                                rs.getString("tipo_moneda"),
                                rs.getString("color"),
                                rs.getString("nomb_cliente_actu"),
                                rs.getString("user_logeado")
                        )
        );
        rpta.setCodigo_error(0);
        rpta.setMsj_error("Listado de movimientos correctos");
        rpta.setLstMovimientos(lstMovi);
        return rpta;
    }

    @Override
    public int iniCliente(int idCliente) {
        String sql = "select count(1) as existe from cliente where id_cliente = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { idCliente }, Integer.class );
    }

    @Override
    public String getNroCuentaCliente(int idCliente) {
        String sql = "select nro_cuenta from cliente where id_cliente = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { idCliente }, String.class);
    }
    
    @Override
    public Double getSaldoCliente(int idCliente) {
        String sql = "SELECT saldo FROM cliente WHERE id_cliente = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { idCliente }, Double.class);
    }

    @Override
    public Double getMontoDepositoCliente(int idCliente) {
        String sql = "SELECT COALESCE(SUM(m.monto), 0) FROM movimiento m WHERE m.id_cliente = ? AND m.tipo_movi = 'EGR' AND DATE(m.fecha_movi) = CURRENT_DATE";
        return jdbcTemplate.queryForObject(sql, new Object[] { idCliente }, Double.class);
    }

    @Override
    public int getCantDepositosCliente(int idCliente) {
        String sql = "SELECT COUNT(1) FROM movimiento m WHERE m.id_cliente = ? AND m.tipo_movi = 'EGR' AND DATE(m.fecha_movi) = CURRENT_DATE";
        return jdbcTemplate.queryForObject(sql, new Object[] { idCliente }, Integer.class);
    }
}
package reto4.controller;
import java.sql.SQLException;
import java.util.List;

import reto4.model.dao.DeudasPorProyectoDao;
import reto4.model.dao.ProyectoBancoDao;
import reto4.model.dao.ComprasLiderDao;
import reto4.model.vo.ComprasDeLiderVo;
import reto4.model.vo.DeudasPorProyectoVo;
import reto4.model.vo.ProyectoBancoVo;

public class ReportesController {
    private ProyectoBancoDao proyectoBancoDao;
    private DeudasPorProyectoDao deudasPorProyectoDao;
    private ComprasLiderDao comprasDeLiderDao;
    
    public ReportesController() {
        proyectoBancoDao = new ProyectoBancoDao();
        deudasPorProyectoDao = new DeudasPorProyectoDao();
        comprasDeLiderDao = new ComprasLiderDao();
    }

    // metodo para bancos, deudas y lideres
    public List<ProyectoBancoVo> listaTotalProyectoBanco(String banco) throws SQLException {
        return proyectoBancoDao.listar(banco);   
    }
    public List<DeudasPorProyectoVo> listaTotalDeudasProyecto(Double limite) throws SQLException {
        return deudasPorProyectoDao.listar(limite);   
    }
    public List<ComprasDeLiderVo> listaTotalcomprasLider() throws SQLException {
        return comprasDeLiderDao.listar();   
    }
}

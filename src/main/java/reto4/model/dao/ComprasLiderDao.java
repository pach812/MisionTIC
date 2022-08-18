package reto4.model.dao;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import reto4.model.vo.ComprasDeLiderVo;
import reto4.util.JDBUtilities;

public class ComprasLiderDao {
    public List<ComprasDeLiderVo> listar() throws SQLException {
        ArrayList<ComprasDeLiderVo> respuesta = new ArrayList<ComprasDeLiderVo>();
        Connection conn=JDBUtilities.getConnection();
        Statement ps =null;
        ResultSet rs = null;
        //aqui se agrega la tercera consulta!!!!!!
        String consulta="SELECT L.NOMBRE ||' '||L.PRIMER_APELLIDO ||' '||L.SEGUNDO_APELLIDO AS LIDER,SUM(C.CANTIDAD * MC.PRECIO_UNIDAD) AS VALOR "
                        +"FROM LIDER L "
                        +"JOIN PROYECTO P ON (P.ID_LIDER = L.ID_LIDER) "
                        +"JOIN COMPRA C ON (P.ID_PROYECTO = C.ID_PROYECTO) "
                        +"JOIN MATERIALCONSTRUCCION MC ON (C.ID_MATERIALCONSTRUCCION =MC.ID_MATERIALCONSTRUCCION) "
                        +"GROUP BY L.NOMBRE ||' '||L.PRIMER_APELLIDO ||' '||L.SEGUNDO_APELLIDO "
                        +"ORDER BY VALOR DESC "
                        +"LIMIT 10";
        
        try{
            ps = conn.createStatement();
            rs=ps.executeQuery(consulta);

            while (rs.next()){
                ComprasDeLiderVo objeto = new ComprasDeLiderVo();
                objeto.setLider(rs.getString("lider"));
                objeto.setValor(rs.getDouble("valor"));
                respuesta.add(objeto);

            }

        }finally{
            if (rs != null){
                rs.close();
            } if (ps != null){
                ps.close();
            } if (conn != null){
                conn.close();
            }
        }
        return respuesta;
    }    
}

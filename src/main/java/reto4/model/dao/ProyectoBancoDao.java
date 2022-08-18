package reto4.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import reto4.model.vo.ProyectoBancoVo;
import reto4.util.JDBUtilities;

public class ProyectoBancoDao {
    public List<ProyectoBancoVo> listar(String banco) throws SQLException {
        ArrayList<ProyectoBancoVo> respuesta = new ArrayList<ProyectoBancoVo>();
        Connection conn=JDBUtilities.getConnection();
        PreparedStatement ps =null;
        ResultSet rs = null;
        // hay que pasarlas a mayusculas 
        String consulta="SELECT P.ID_PROYECTO AS ID, P.CONSTRUCTORA, P.CIUDAD, P.CLASIFICACION, "
                        +"T.ESTRATO, L.NOMBRE||' '||L.PRIMER_APELLIDO||' '||L.SEGUNDO_APELLIDO AS LIDER "
                        +"FROM PROYECTO P "
                        +"JOIN TIPO T ON (P.ID_TIPO = T.ID_TIPO) "
                        +"JOIN LIDER L ON (P.ID_LIDER = L.ID_LIDER) "
                        +"WHERE P.BANCO_VINCULADO = ? "
                        +"ORDER BY FECHA_INICIO DESC, CIUDAD, CONSTRUCTORA ";
        
        try{
            ps =conn.prepareStatement(consulta);
            ps.setString(1, banco);
            rs=ps.executeQuery();

            while (rs.next()){
                ProyectoBancoVo objeto = new ProyectoBancoVo();
                objeto.setId(rs.getInt("id"));
                objeto.setConstructora(rs.getString("constructora"));
                objeto.setCiudad(rs.getString("ciudad"));
                objeto.setClasificacion(rs.getString("clasificacion"));
                objeto.setEstrato(rs.getInt("estrato"));
                objeto.setLider(rs.getString("lider"));
                respuesta.add(objeto);

            }

        }finally{
            if (rs != null){
                rs.close();
            } 
            if (ps != null){
                ps.close();
            } 
            if (conn != null){
                conn.close();
            }
        }
        return respuesta;
    }    
}

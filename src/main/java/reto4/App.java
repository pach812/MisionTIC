package reto4;

import java.sql.SQLException;

import reto4.view.ReportesView;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
        System.out.println("Requerimiento 1");
        ReportesView reportesView = new ReportesView();
        String banco = "Conavi";
        reportesView.proyectosFinanciadosPorBanco(banco);
        System.out.println();
      
        System.out.println("Requerimiento 2");
        ReportesView reportesView1 = new ReportesView();
        reportesView1.totalAdeudadoPorProyectosSuperioresALimite(50000.0);
        System.out.println();
     
        System.out.println("Requerimiento 3");
        ReportesView reportesView2 = new ReportesView();
        reportesView2.lideresQueMasGastan();
     
    }
}

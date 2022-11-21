
import Assets_Indicators.*;

import Development_Indicators.*;
import java.util.ArrayList;
import java.util.Scanner;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import java.util.ArrayList;
import Assets_Indicators.*;


public class LineChart_Development_indicators extends ApplicationFrame {

    public LineChart_Development_indicators(String applicationTitle , String chartTitle , String code, int start , int end, Development_Indicators d,ArrayList<country> c) {
        super(applicationTitle);
        String s1 =  Integer.toString(start);
       String s2 =    Integer.toString(end);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "years from " + s1 + " to " +s2,"value",
                createDataset(code,  start , end, d,c),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart);
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }

    private DefaultCategoryDataset createDataset(String cod, int start , int end, Development_Indicators d,ArrayList<country> c ) {

        String pr = cod;
        //String qr = "temp2";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        int i ;
        for( i=0;i<c.size();i++)
        {
            if((c.get(i).code).equals(cod))
            {
                break;
            }
        }
        ArrayList<? extends  Development_Indicators> temp = new ArrayList<>();
        if(d instanceof CPI)
        {
            temp = c.get(i).cpis;
        }
        if(d instanceof  Deposit_Interest_Rate)
        {
            temp = c.get(i).interest_rates;
        }
        if(d instanceof  Exchange_Rate)
        {
            temp = c.get(i).exchange_rates;
        }

      ArrayList<Double> a1 =   c.get(i).searchbyInterval(temp,start,end);
int w = start;
        for(int p=0;p< a1.size();p++)
        {
           String s1 =  Integer.toString(w);
            double r = a1.get(p);
            dataset.addValue(r,pr,s1);
            w++;

        }

        return dataset;
    }


}
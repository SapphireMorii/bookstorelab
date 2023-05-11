package bookstore.util;

import bookstore.domain.products;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class ChartPie1 {
    //传入数组表示图标的类别
    public void chart(Map<products,String> map) {

        DefaultPieDataset dataset = new DefaultPieDataset();
        Iterator<products> iter = map.keySet().iterator();
        while(iter.hasNext()){
            products key=iter.next();
            String value = map.get(key);
            //设置每个商品所占比重
            dataset.setValue(key.getName(),Double.valueOf(value));
        }

        JFreeChart chart = ChartFactory.createPieChart("销量分析图", // chart
                dataset, // data
                true, // include legend
                true, false);
        setChart(chart);
        setPieChartFont(chart);
        PiePlot pieplot = (PiePlot) chart.getPlot();
        while(iter.hasNext()){
            products key=iter.next();

            //设置每个商品的颜色
            pieplot.setSectionPaint(key.getName(), Color.decode("'#'+Math.floor(Math.random()*256).toString(10)"));
        }


        try {
//			// 创建图形显示面板
//			ChartFrame cf = new ChartFrame("柱状图", chart);
//			// cf.pack();
//			// // 设置图片大小
//			cf.setSize(width, height);
//			// // 设置图形可见
//			cf.setVisible(true);

            // 保存图片到指定文件夹
            ChartUtilities.saveChartAsPNG(new File("D:/javaworkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/itcaststore/admin/images/a.png"), chart, 800, 600);
//	    	ChartUtilities.saveChartAsPNG(new File(""), chart, 800, 600);

            System.out.println(this.getClass().getClassLoader().getResource("").getPath());
            System.err.println("成功");
        } catch (Exception e) {
            System.err.println("创建图形时出错");
        }
    }


    //解决中文乱码问题
    public void setPieChartFont(JFreeChart freeChart){
        PiePlot pieChart = (PiePlot) freeChart.getPlot();
        pieChart.setLabelFont(new Font("宋体", Font.BOLD, 20));
        TextTitle textTitle = freeChart.getTitle();
        textTitle.setFont(new Font("宋体", Font.ITALIC, 25));
        LegendTitle legendTitle = freeChart.getLegend();
        legendTitle.setItemFont(new Font("宋体", Font.BOLD+Font.ITALIC, 10));
    }

    public static void setChart(JFreeChart chart) {
        chart.setTextAntiAlias(true);

        PiePlot pieplot = (PiePlot) chart.getPlot();
        // 设置图表背景颜色
        pieplot.setBackgroundPaint(ChartColor.WHITE);


        pieplot.setLabelBackgroundPaint(null);// 标签背景颜色


        pieplot.setLabelOutlinePaint(null);// 标签边框颜色


        pieplot.setLabelShadowPaint(null);// 标签阴影颜色


        pieplot.setOutlinePaint(null); // 设置绘图面板外边的填充颜色


        pieplot.setShadowPaint(null); // 设置绘图面板阴影的填充颜色

        pieplot.setSectionOutlinesVisible(false);
        pieplot.setNoDataMessage("没有可供使用的数据！");

    }
}

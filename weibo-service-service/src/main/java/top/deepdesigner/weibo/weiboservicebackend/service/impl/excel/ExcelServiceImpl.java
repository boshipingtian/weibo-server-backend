/**
 * Copyright(C),2015‐2023,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.service.impl.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xddf.usermodel.LineCap;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xddf.usermodel.chart.MarkerStyle;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFLineChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFLineChartData.Series;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import top.deepdesigner.weibo.weiboservicebackend.service.api.excel.ExcelService;
import top.deepdesigner.weibo.weiboservicebackend.utils.MockUtil;

/**
 * <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2023/9/7 10:26
 * @Version: 1.0.0
 */
@Slf4j
@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public ByteArrayOutputStream exportBarChart() {
        List<BigDecimal> bigDecimal1 = MockUtil.randomList(96, 100, 80);
        List<BigDecimal> bigDecimal2 = MockUtil.randomList(96, 120, 90);
        List<String> times = MockUtil.getTimes("2023-01-01", "2023-01-02", Calendar.MINUTE, 15, "yyyy-MM-dd HH:mm");
        String sheetName = "Sheet1";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            XSSFSheet sheet = wb.createSheet(sheetName);
            //第一行，时间
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("时间");
            for (int i = 1; i < times.size() + 1; i++) {
                cell = row.createCell(i);
                cell.setCellValue(times.get(i - 1));
            }

            // 第二行，空间
            row = sheet.createRow(1);
            cell = row.createCell(0);
            cell.setCellValue("用电1");
            for (int i = 1; i < bigDecimal1.size() + 1; i++) {
                cell = row.createCell(i);
                cell.setCellValue(bigDecimal1.get(i - 1).toString());
            }

            // 第三行，空间
            row = sheet.createRow(2);
            cell = row.createCell(0);
            cell.setCellValue("用电2");
            for (int i = 1; i < bigDecimal2.size() + 1; i++) {
                cell = row.createCell(i);
                cell.setCellValue(bigDecimal2.get(i - 1).toString());
            }
            drawPaint(sheet, times, bigDecimal1, bigDecimal2);
            // 输出
            wb.write(byteArrayOutputStream);
            return byteArrayOutputStream;
        } catch (IOException e) {
            log.info("Error writing", e);
        }
        return byteArrayOutputStream;
    }


    @SafeVarargs
    private void drawPaint(XSSFSheet sheet, List<String> xAxis, List<BigDecimal>... yAxis) {
        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        //前四个默认0，[0,5]：从0列5行开始;[7,26]:宽度7个单元格，26向下扩展到26行
        //默认宽度(14-8)*12
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 60, 26);
        //创建一个chart对象
        XSSFChart chart = drawing.createChart(anchor);
        //标题
        chart.setTitleText("标题");
        //标题覆盖
        chart.setTitleOverlay(false);

        //图例位置
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.TOP);

        //分类轴标(X轴),标题位置
        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        bottomAxis.setTitle("时间");
        //值(Y轴)轴,标题位置
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setTitle("数值");
        //分类轴标(X轴)数据
        XDDFCategoryDataSource x = XDDFDataSourcesFactory.fromArray(xAxis.toArray(new String[]{}));
        //LINE：折线图，
        XDDFLineChartData data = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);
        Stream.of(yAxis).forEach(ts -> {
            //每根折线
            XDDFNumericalDataSource<BigDecimal> y = XDDFDataSourcesFactory.fromArray(ts.toArray(new BigDecimal[]{}));
            this.createSeries(data, x, y);
        });
        //绘制
        chart.plot(data);
    }

    private <E extends Number> void createSeries(XDDFLineChartData chartData, XDDFCategoryDataSource axis,
        XDDFNumericalDataSource<E> data) {
        XDDFLineChartData.Series series = (Series) chartData.addSeries(axis, data);
        //折线图例标题
        series.setTitle("时间", null);
        //直线
        series.setSmooth(false);
        //设置标记大小
        series.setMarkerSize((short) 6);
        //设置标记样式，星星
        series.setMarkerStyle(MarkerStyle.STAR);

        XDDFLineProperties xddfLineProperties = new XDDFLineProperties();
        xddfLineProperties.setLineCap(LineCap.SQUARE);

        series.setLineProperties(xddfLineProperties);
    }

}
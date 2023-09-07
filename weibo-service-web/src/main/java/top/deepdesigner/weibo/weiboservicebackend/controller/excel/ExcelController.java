/**
 * Copyright(C),2015‐2023,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.controller.excel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.deepdesigner.weibo.weiboservicebackend.service.api.excel.ExcelService;

/**
 * <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2023/9/7 10:23
 * @Version: 1.0.0
 */
@RestController
@Api(value = "Excel", tags = "Excel")
@RequestMapping(value = "/excel")
public class ExcelController {

    private ExcelService excelService;

    @Autowired
    public void setExcelService(ExcelService excelService) {
        this.excelService = excelService;
    }

    @ApiOperation(value = "导出 - 柱状图")
    @GetMapping("/barChart")
    public void barChart(HttpServletResponse servletResponse) throws IOException {
        ByteArrayOutputStream outputStream = excelService.exportBarChart();

        // 新建内存流
        ServletOutputStream servletOutputStream = servletResponse.getOutputStream();
        String fileName = new String("柱状图.xlsx".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        servletResponse.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        servletResponse.addHeader("Content-Length", String.valueOf(outputStream.toByteArray().length));
        servletResponse.setContentType("application/octet-stream");
        servletOutputStream.write(outputStream.toByteArray());
        servletOutputStream.flush();
        servletOutputStream.close();
    }
}
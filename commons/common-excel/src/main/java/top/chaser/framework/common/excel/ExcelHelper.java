package top.chaser.framework.common.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.NonNull;

public class ExcelHelper extends EasyExcel {
    public static MybatisExcelWriter.MybatisExcelWriterBuilder mybatisWrite(@NonNull ExcelWriter excelWriter, @NonNull WriteSheet writeSheet) {
        return MybatisExcelWriter.builder().excelWriter(excelWriter).writeSheet(writeSheet);
    }

    public static MybatisExcelWriter.MybatisExcelWriterBuilder mybatisWrite(@NonNull ExcelWriter excelWriter) {
        return MybatisExcelWriter.builder().excelWriter(excelWriter).writeSheet(EasyExcel.writerSheet(1).build());
    }
}
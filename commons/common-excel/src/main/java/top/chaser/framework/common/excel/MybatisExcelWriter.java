package top.chaser.framework.common.excel;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.Builder;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Builder
@Slf4j
public class MybatisExcelWriter {
    @Builder.Default
    protected int batchSize = 1000;
    private ExcelWriter excelWriter;
    private WriteSheet writeSheet;

    public MybatisExcelWriter doWrite(@NonNull SqlSessionFactory sqlSessionFactory, @NonNull String statement, @NonNull Object params) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Cursor cursor = sqlSession.selectCursor(statement, params);
        List coll = new ArrayList(batchSize+100);
        try {
            cursor.forEach(item -> {
                coll.add(item);
                if(coll.size()>=batchSize){
                    excelWriter.write(coll,writeSheet);
                    coll.clear();
                    int currentIndex = cursor.getCurrentIndex()+1;
                    log.debug("excel write:{}",currentIndex);
                }

            });
            if(coll.size()>0){
                excelWriter.write(coll,writeSheet);
            }
            int currentIndex = cursor.getCurrentIndex()+1;
            log.debug("excel write:{}",currentIndex);
        } finally {
            try {
                if(cursor.isOpen()){
                    cursor.close();
                    log.trace("cursor closed");
                }
            } catch (IOException e) {
                log.error("close cursor error",e);
            }
            excelWriter.finish();
        }
        return this;
    }
}

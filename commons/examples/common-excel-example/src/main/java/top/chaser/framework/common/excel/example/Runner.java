package top.chaser.framework.common.excel.example;

import cn.hutool.core.lang.UUID;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import top.chaser.framework.common.excel.ExcelHelper;
import top.chaser.framework.common.excel.example.model.MallInfo;
import top.chaser.framework.common.excel.example.service.MallInfoService;

import java.util.Arrays;
import java.util.HashMap;

@Component
@Slf4j
public class Runner implements ApplicationRunner {
    @Autowired
    ApplicationContext ctx;

    @Autowired
    MallInfoService mallInfoService;

    @Autowired
    SqlSessionFactory sqlSession;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
//        for (int i = 0; i <1000000 ; i++) {
//            MallInfo mallInfo = new MallInfo();
//            mallInfo.setAgentId("scanFoo"+i);
//            mallInfo.setMallName("sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)org.springframework.dao.InvalidDataAccessApiUsageException"+i);
//            mallInfo.setMallNbr(UUID.randomUUID().toString());
//            mallInfo.setMallType(""+i);
//            mallInfoService.insertSelective(mallInfo);
//        }
        ExcelWriter excelWriter = ExcelHelper.write("/Users/yzb/Downloads/mall.xlsx", MallInfo.class).build();
        WriteSheet writeSheet = ExcelHelper.writerSheet("ceshi").build();
        ExcelHelper.mybatisWrite(excelWriter,
                writeSheet
                )
                .build().doWrite(sqlSession, "top.chaser.framework.common.excel.example.mapper.MallInfoMapper.selectForExcel", new MallInfo());
        log.error("9999");
    }
}

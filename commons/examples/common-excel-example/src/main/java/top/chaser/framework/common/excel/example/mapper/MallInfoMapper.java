package top.chaser.framework.common.excel.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.cursor.Cursor;
import top.chaser.framework.common.excel.example.model.MallInfo;
import top.chaser.framework.starter.tkmybatis.mapper.TkBaseMapper;

@Mapper
public interface MallInfoMapper extends TkBaseMapper<MallInfo> {
    Cursor<MallInfo> selectForExcel(MallInfo mallInfo);
}
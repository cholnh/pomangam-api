package com.mrporter.pomangam.common.util.queryRunner;

import lombok.AllArgsConstructor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class QueryRunnerImpl {

    QueryRunner queryRunner;

    public List<Map<String, Object>> query(String sql, Object...obj) throws Exception {
        List<Map<String, Object>> listOfMaps = null;
        listOfMaps = queryRunner.query(sql, new MapListHandler(), obj);
        return listOfMaps;
    }

    public Map<String, Object> getOne(String sql, Object...obj) throws Exception {
        List<Map<String, Object>> listOfMaps = query(sql, obj);
        return listOfMaps == null
                ? null
                : listOfMaps.get(0);
    }

    public void update(String sql, Object...obj) throws Exception {
        queryRunner.update(sql, obj);
    }
}

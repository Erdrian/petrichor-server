package com.petrichor.sincerity.api;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

@Data
public class CommonPage<T> {
    private long total;
    private List<T> records;

    public static <T> CommonResult<CommonPage<T>> restPage(List<T> list) {
        CommonPage<T> result = new CommonPage<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        result.setRecords(pageInfo.getList());
        result.setTotal(pageInfo.getTotal());
        return CommonResult.success(result);
    }
}

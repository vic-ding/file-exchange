package com.springboot.weking.core.pages;

import com.github.pagehelper.PageInfo;


public class PageUtils {
    public static Page convert(PageInfo pageInfo) {
        Page page = new Page();
        page.setTotal(pageInfo.getTotal());
        page.setPage(pageInfo.getPageNum());
        page.setPages(pageInfo.getPages());
        page.setSize(pageInfo.getSize());
        page.setRows(pageInfo.getList());
        return page;
    }
}

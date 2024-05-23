package com.pjt.core.common.paging;

import lombok.Data;

@Data
public class Paging {

    private Integer page;
    private Integer size;
    private String sort;

    public Integer getPage() {
        if(page == null || page == 0) {
            page = 1;
        }

        return page;
    }

    public Integer getSize() {
        if(size == null || size == 0) {
            size = 10;
        }
        return size;
    }

    public Integer getOffset() { return (this.getPage() - 1 ) * this.getSize(); }
}

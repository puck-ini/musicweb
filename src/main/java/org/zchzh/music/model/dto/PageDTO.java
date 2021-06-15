package org.zchzh.music.model.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @author zengchzh
 * @date 2021/6/12
 */
@Data
public class PageDTO<T> implements Serializable {

    private Integer currentPage;
    private Integer currentSize;
    private Long total;
    private Integer totalPages;
    private List<T> content;

    public PageDTO(){}

    public PageDTO(Page<T> page) {
        this.currentPage = page.getNumber();
        this.currentSize = page.getNumberOfElements();
        this.total = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.content = page.getContent();
    }
}

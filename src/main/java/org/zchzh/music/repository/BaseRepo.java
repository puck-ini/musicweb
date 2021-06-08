package org.zchzh.music.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.List;

/**
 * @author zengchzh
 * @date 2021/5/11
 */
@NoRepositoryBean
public interface BaseRepo<ENTITY, ID> extends JpaRepository<ENTITY, ID> {


    /**
     * 自定义查找
     * @param ids id 列表
     * @param sort 排序方式
     * @return 返回实体列表
     */
    List<ENTITY> findAllById(Collection<ID> ids, Sort sort);

    /**
     * 自定义查找
     * @param ids id 列表
     * @param pageable 分页方式
     * @return 返回实体列表
     */
    Page<ENTITY> findAllById(Collection<ID> ids, Pageable pageable);
}

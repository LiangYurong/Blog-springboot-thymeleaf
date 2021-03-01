package com.springbootblog.service;

import com.springbootblog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    /**
     * 新增分类
     * @param type
     * @return
     */
    Type saveType(Type type);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Type getType(Long id);

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    /**
     * 首页的分类展示
     * @return
     */
    List<Type> listTypeTop(Integer size);

    /**
     * 更新
     * @param id
     * @param type
     * @return
     */
    Type updateType(Long id,Type type);

    /**
     * 删除
     * @param id
     * @return
     */
    void deleteType(Long id);

    Type getTypeByName(String name);
}

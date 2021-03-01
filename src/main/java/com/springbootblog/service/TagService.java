package com.springbootblog.service;

import com.springbootblog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    /**
     * 新增分类
     * @param tag
     * @return
     */
    Tag saveTag(Tag tag);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Tag getTag(Long id);

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<Tag> listTag(Pageable pageable);
    List<Tag> listTag();
    List<Tag> listTag(String ids);
    List<Tag> listTagTop(Integer size);

    /**
     * 更新
     * @param id
     * @param tag
     * @return
     */
    Tag updateTag(Long id, Tag tag);

    /**
     * 删除
     * @param id
     * @return
     */
    void deleteTag(Long id);

    Tag getTagByName(String name);
}

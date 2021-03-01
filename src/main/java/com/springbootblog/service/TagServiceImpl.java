package com.springbootblog.service;

import com.springbootblog.NotFoundException;
import com.springbootblog.dao.TagRepository;
import com.springbootblog.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.findById(id).get();
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }


    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAllById(convertToList(ids));
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        //倒序
        Sort sort=Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0, size,sort);
        return tagRepository.findTop(pageable);
    }

    /**
     *
     * @param ids
     * @return
     */
    private List<Long> convertToList(String ids){
        List<Long> list=new ArrayList<>();
        if(!"".equals(ids) || ids!=null){
            String[] idsArray=ids.split(",");
            for (int i = 0; i <idsArray.length ; i++) {
                list.add(new Long(idsArray[i]));
            }
        }
        return list;
    }

    /**
     * 通过id查询是否已存在对象tag1，存在的话将修改后的对象覆盖已存在的对象
     * @param id
     * @param tag
     * @return
     */
    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag tag1 = tagRepository.findById(id).get();
        if(tag1==null){
            throw new NotFoundException("不存在该类型");
        }
        // 通过反射将一个对象tag的值赋值个另外一个对象tag1（前提是对象中属性的名字相同）。前复制给后
        BeanUtils.copyProperties(tag,tag1);
        return tagRepository.save(tag1);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }
}

package com.ayang.ai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ayang.ai.model.entity.Post;
import java.util.Date;
import java.util.List;

/**
 * 帖子数据库操作
 *
 * @author <a href="https://github.com/AyangCodeLib">阿洋努力学习</a>
 * 
 */
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 查询帖子列表（包括已被删除的数据）
     */
    List<Post> listPostWithDelete(Date minUpdateTime);

}





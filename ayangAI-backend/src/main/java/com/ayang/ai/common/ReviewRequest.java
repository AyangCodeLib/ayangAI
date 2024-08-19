package com.ayang.ai.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 审核请求
 *
 * @author <a href="https://github.com/AyangCodeLib">阿洋努力学习</a>
 * 
 */
@Data
public class ReviewRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 审核状态：0-待审核， 1-通过， 2-拒绝
     */
    private Integer reviewStatus;

    /**
     * 审合信息
     */
    private String reviewMessage;

    private static final long serialVersionUID = 1L;
}
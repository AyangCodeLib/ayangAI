package com.ayang.ai.model.dto.question;

import lombok.Data;

import java.io.Serializable;

/**
 * 更新题目请求
 *
 * @author <a href="https://github.com/AyangCodeLib">阿洋努力学习</a>
 * 
 */
@Data
public class QuestionUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 题目内容（json格式）
     */
    private QuestionContentDTO questionContent;


    private static final long serialVersionUID = 1L;
}
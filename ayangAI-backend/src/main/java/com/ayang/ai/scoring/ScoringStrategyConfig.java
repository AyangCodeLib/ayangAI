package com.ayang.ai.scoring;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 评分策略管理器
 *
 * @author <a href="https://github.com/AyangCodeLib">阿洋努力学习</a>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ScoringStrategyConfig {

    /**
     * 应用类型
     *
     * @return
     */
    int appType();

    /**
     * 评分类型
     *
     * @return
     */
    int scoringStrategy();
}

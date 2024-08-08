package com.ayang.ai.scoring;

import com.ayang.ai.common.ErrorCode;
import com.ayang.ai.exception.BusinessException;
import com.ayang.ai.exception.ThrowUtils;
import com.ayang.ai.model.entity.App;
import com.ayang.ai.model.entity.UserAnswer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评分策略执行器
 *
 * @author <a href="https://github.com/AyangCodeLib">阿洋努力学习</a>
 */
@Service
public class ScoringStrategyExecutor {

    /**
     * 策略列表
     */
    @Resource
    private List<ScoringStrategy> scoringStrategies;


    public UserAnswer doScore(List<String> choices, App app) throws Exception {
        Integer appType = app.getAppType();
        Integer scoringStrategy = app.getScoringStrategy();
        ThrowUtils.throwIf(appType == null || scoringStrategy == null, ErrorCode.SYSTEM_ERROR, "应用配置有误，未找到匹配的策略");
        // 根据注解获取策略
        for (ScoringStrategy strategy : scoringStrategies) {
            if (strategy.getClass().isAnnotationPresent(ScoringStrategyConfig.class)) {
                ScoringStrategyConfig scoringStrategyConfig = strategy.getClass().getAnnotation(ScoringStrategyConfig.class);
                if (scoringStrategyConfig.appType() == appType && scoringStrategyConfig.scoringStrategy() == scoringStrategy) {
                    return strategy.doScore(choices, app);
                }
            }
        }
        throw new BusinessException(ErrorCode.SYSTEM_ERROR, "应用配置有误，未找到匹配的策略");
    }


}

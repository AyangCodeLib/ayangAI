package com.ayang.ai.scoring;

import com.ayang.ai.common.ErrorCode;
import com.ayang.ai.exception.BusinessException;
import com.ayang.ai.exception.ThrowUtils;
import com.ayang.ai.model.entity.App;
import com.ayang.ai.model.entity.UserAnswer;
import com.ayang.ai.model.enums.AppScoringStrategyEnum;
import com.ayang.ai.model.enums.AppTypeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评分策略管理器
 *
 * @author <a href="https://github.com/AyangCodeLib">阿洋努力学习</a>
 * @deprecated 实现简单，但后续扩展起来就low了
 */
@Service
@Deprecated
public class ScoringStrategyContext {

    @Resource
    private CustomizeScoreScoringStrategy customizeScoreScoringStrategy;

    @Resource
    private CustomizeTestScoringStrategy customizeTestScoringStrategy;

    public UserAnswer doScore(List<String> choiceList, App app) throws Exception {
        AppTypeEnum appTypeEnum = AppTypeEnum.getEnumByValue(app.getAppType());
        AppScoringStrategyEnum scoringStrategyEnum = AppScoringStrategyEnum.getEnumByValue(app.getScoringStrategy());
        ThrowUtils.throwIf(appTypeEnum == null || scoringStrategyEnum == null, ErrorCode.PARAMS_ERROR, "应用配置有无，未找到对应的应用类型或评分策略类型");

        // 根据不同的应用类别和评分策略，选择不同的评分策略执行
        switch (appTypeEnum) {
            case SCORE:
                switch (scoringStrategyEnum) {
                    case CUSTOM:
                        return customizeScoreScoringStrategy.doScore(choiceList, app);
                    case AI:
                        break;
                }
                break;
            case EVALUATION:
                switch (scoringStrategyEnum) {
                    case CUSTOM:
                        return customizeTestScoringStrategy.doScore(choiceList, app);
                    case AI:
                        break;
                }
                break;
        }
        throw new BusinessException(ErrorCode.SYSTEM_ERROR, "应用配置有误，未找到匹配的策略");
    }
}

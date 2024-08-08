package com.ayang.ai.scoring;

import cn.hutool.json.JSONUtil;
import com.ayang.ai.common.ErrorCode;
import com.ayang.ai.exception.ThrowUtils;
import com.ayang.ai.model.dto.question.QuestionContentDTO;
import com.ayang.ai.model.entity.App;
import com.ayang.ai.model.entity.Question;
import com.ayang.ai.model.entity.ScoringResult;
import com.ayang.ai.model.entity.UserAnswer;
import com.ayang.ai.model.vo.QuestionVO;
import com.ayang.ai.service.QuestionService;
import com.ayang.ai.service.ScoringResultService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * 自定义打分类
 *
 * @author <a href="https://github.com/AyangCodeLib">阿洋努力学习</a>
 */
@ScoringStrategyConfig(appType = 0, scoringStrategy = 0)
public class CustomizeScoreScoringStrategy implements ScoringStrategy {

    @Resource
    private QuestionService questionService;

    @Resource
    private ScoringResultService scoringResultService;

    @Override
    public UserAnswer doScore(List<String> choices, App app) throws Exception {
        // 校验
        ThrowUtils.throwIf(app == null, ErrorCode.PARAMS_ERROR);
        Long appId = app.getId();
        // 1. 根据题目id查询到题目和题目结果信息（按照分数降序排序）
        Question question = questionService.lambdaQuery().eq(Question::getAppId, appId).one();
        List<ScoringResult> scoringResultList = scoringResultService.lambdaQuery()
                .eq(ScoringResult::getAppId, appId)
                .orderByDesc(ScoringResult::getResultScoreRange)
                .list();

        // 2. 统计用户的总得分
        int totalScore = 0;
        QuestionVO questionVO = QuestionVO.objToVo(question);
        List<QuestionContentDTO> questionContent = questionVO.getQuestionContent();

        // 遍历题目列表
        for (int i = 0; i < questionContent.size(); i++) {
            // 获取对应题号的回答
            String answer = choices.get(i);
            // 获取对应题号的题目
            QuestionContentDTO contentDTO = questionContent.get(i);
            // 遍历题目中的选项
            for (QuestionContentDTO.Option option : contentDTO.getOptions()) {
                // 如果答案和选择匹配
                if (option.getKey().equals(answer)) {
                    Integer score = Optional.ofNullable(option.getScore()).orElse(0);
                    totalScore += score;
                }
            }
        }

        // 3. 遍历得分结果，得到最终结果
        ScoringResult maxScoringResult = scoringResultList.get(0);

        for (ScoringResult result : scoringResultList) {
            Integer resultScoreRange = result.getResultScoreRange();
            if (totalScore >= resultScoreRange) {
                maxScoringResult = result;
                break;
            }
        }

        // 4. 构造返回值，填充答案对象的属性
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setAppId(appId);
        userAnswer.setAppType(app.getAppType());
        userAnswer.setScoringStrategy(app.getScoringStrategy());
        userAnswer.setChoices(JSONUtil.toJsonStr(choices));
        userAnswer.setResultId(maxScoringResult.getId());
        userAnswer.setResultName(maxScoringResult.getResultName());
        userAnswer.setResultDesc(maxScoringResult.getResultDesc());
        userAnswer.setResultPicture(maxScoringResult.getResultPicture());
        userAnswer.setResultScore(totalScore);
        return null;
    }
}

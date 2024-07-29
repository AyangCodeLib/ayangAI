import {View} from "@tarojs/components";
import {AtButton, AtRadio} from "taro-ui";
import "taro-ui/dist/style/components/button.scss"; // 按需引入
import "./index.scss";
import GlobalFooter from "../../components/GlobalFooter";
import questions from "../../data/questions.json";
import {useState} from "react";

/**
 * 做题页面
 */
export default () => {
  const question = questions[0];
  const questionOptions: any = question.options.map((option) => {
    return { label: `${option.key}. ${option.value}`, value: option.key };
  });
  // 当前题目序号（从1开始）
  const [current, setCurrent] = useState<number>(1);

  return (
    <View className="doQuestionPage">
      <View className="at-article__h1 title">
        {current}、{question.title}
      </View>
      <View className="options-wrapper">
        <AtRadio options={questionOptions} />
      </View>
      <AtButton type="primary" circle className="controlBtn">
        下一题
      </AtButton>
      <AtButton type="primary" circle className="controlBtn">
        查看结果
      </AtButton>
      <AtButton circle className="controlBtn">
        上一题
      </AtButton>
      <GlobalFooter />
    </View>
  );
};

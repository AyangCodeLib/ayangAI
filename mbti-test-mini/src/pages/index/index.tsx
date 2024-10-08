import {Image, View} from "@tarojs/components";
import {AtButton} from "taro-ui";
import Taro from "@tarojs/taro";

import "taro-ui/dist/style/components/button.scss"; // 按需引入
import headerBg from "../../assets/headerBg.jpg";
import "./index.scss";
import GlobalFooter from "../../components/GlobalFooter";

/**
 * 主页
 */
export default () => {
  return (
    <View className="indexPage">
      <View className="at-article__h1 title">MBTI 性格测试</View>
      <View className="at-article__h2 subTitle">
        只需2分钟，就能非常准确地描述出你是谁，以及你的性格特点
      </View>
      <AtButton
        type="primary"
        circle
        className="enterBtn"
        onClick={() => {
          Taro.navigateTo({
            url: "/pages/doQuestion/index",
          });
        }}
      >
        开始测试
      </AtButton>
      <Image
        className="headerBg"
        style={{
          width: "100%",
          position: "fixed",
          bottom: "16px",
          textAlign: "center"
        }}
        mode="widthFix"
        src={headerBg}
      />
      <GlobalFooter />
    </View>
  );
};

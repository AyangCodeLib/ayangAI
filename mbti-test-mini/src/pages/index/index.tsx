import { Text, View } from "@tarojs/components";
import { AtBadge, AtButton } from "taro-ui";

import "taro-ui/dist/style/components/button.scss"; // 按需引入
import "./index.scss";

/**
 * 主页
 */
export default () => {
  return (
    <View className="index">
      <Text>Hello world!</Text>
      <AtButton type="primary">I need Taro UI</AtButton>
      <Text>Taro UI 支持 Vue 了吗？！</Text>
      <AtButton type="primary" circle>
        支持
      </AtButton>
      <AtBadge value={10} maxValue={99}>
        <AtButton size="small">按钮</AtButton>
      </AtBadge>
      <Text>啊啊啊啊啊</Text>
      <AtButton type="secondary" circle>
        来111
      </AtButton>
    </View>
  );
};

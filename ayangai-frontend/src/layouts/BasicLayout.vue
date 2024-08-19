<template>
  <div id="BasicLayout">
    <a-layout style="height: 100vh">
      <a-layout-header class="header">
        <GlobalHeader />
      </a-layout-header>
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
      <a-layout-footer class="footer">
        <a
          href="https://baidu.com"
          target="_blank"
          style="text-decoration: none"
          >阿洋AI by 程序员阿洋</a
        >
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<script setup lang="ts">
import GlobalHeader from "@/components/GlobalHeader.vue";
</script>

<style scoped>
#BasicLayout {
}

.header {
  margin-bottom: 16px;
  box-shadow: #eee 1px 1px 5px;
}

.content {
  margin-bottom: 28px;
  padding: 20px;
  background: linear-gradient(to right, #fefefe, #fff);
}

.footer {
  padding: 16px;
  text-align: center;
  backgroud: #efefef;
}
</style>

<template>
  <div class="noAuthPage">无权限</div>
</template>

<script setup lang="ts">
import { useLoginUserStore } from "@/store/userStore";

const loginUserStore = useLoginUserStore();
</script>

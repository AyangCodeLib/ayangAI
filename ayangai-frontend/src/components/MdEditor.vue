<template>
  <Editor :value="value" :plugins="plugins" @change="hanleChange" />
</template>

<script setup lang="ts">
import gfm from "@bytemd/plugin-gfm";
import highlight from "@bytemd/plugin-highlight";
import { Editor } from "@bytemd/vue-next";
import { ref } from "vue";

const plugins = [gfm(), highlight()];

const value = ref("");

const handleChange = (v: string) => {
  value.value = v;
};
</script>

<style scoped></style>

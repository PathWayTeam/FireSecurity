<script setup>
import TheLayout from './components/TheLayout.vue'
</script>

<template>
  <TheLayout />
</template>

<style>
@import 'leaflet/dist/leaflet.css';

:root {
  --primary-color: #42b883;
  --header-height: 60px;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: Arial, sans-serif;
}

.page-enter-active,
.page-leave-active {
  transition: opacity 0.3s;
}

.page-enter-from,
.page-leave-to {
  opacity: 0;
}
</style>
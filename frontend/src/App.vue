<!-- App.vue -->
<template>
  <div id="app">
    <AddQuestion />
  </div>
</template>

<script>
import AddQuestion from './components/AddQuestion.vue';

export default {
  name: 'App',
  components: {
    AddQuestion
  }
};
</script>

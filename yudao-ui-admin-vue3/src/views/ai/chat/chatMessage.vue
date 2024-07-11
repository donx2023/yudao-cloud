<template>
  <div :class="['chat-message', messageType]">
    <p>{{ message.text }}</p>
  </div>
</template>

<script>
export default {
  name: 'ChatMessage',
  props: {
    message: {
      type: Object,
      required: true
    }
  },
  computed: {
    messageType() {
      return this.message.isUser ? 'user-message' : 'bot-message';
    }
  }
}
</script>

<style scoped>
.chat-message {
  padding: 10px;
  margin: 5px 0;
  border-radius: 5px;
}
.user-message {
  background-color: #daf8cb;
  text-align: right;
}
.bot-message {
  background-color: #f1f0f0;
  text-align: left;
}
</style>
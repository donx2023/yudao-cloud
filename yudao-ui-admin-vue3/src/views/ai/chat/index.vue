<template>
  <el-container>
    <!-- 左侧：会话列表 -->
    <el-aside width="260px" class="conversation-container">
      <!-- 左顶部：新建对话 -->
      <el-button class="w-1/1" type="primary">
        <Icon icon="ep:plus" class="mr-5px" />
        新建对话
      </el-button>
      <!-- 左顶部：搜索对话 -->
      <el-input
        v-model="searchName"
        class="mt-10px"
        placeholder="搜索历史记录"
        @keyup="searchConversation"
      >
        <template #prefix>
          <Icon icon="ep:search" />
        </template>
      </el-input>
      <!-- 左中间：对话列表 -->
      <div class="conversation-list" :style="{ height: leftHeight + 'px' }">
        <el-row v-for="conversation in conversationList" :key="conversation.id">
          <div
            :class="conversation.id === conversationId ? 'conversation active' : 'conversation'"
            @click="changeConversation(conversation)"
          >
            <el-image :src="conversation.avatar" class="avatar" />
            <span class="title">{{ conversation.title }}</span>
            <span class="button">
              <!-- TODO 芋艿：缺置顶按钮 -->
              <el-icon title="编辑" @click="updateConversationTitle(conversation)">
                <Icon icon="ep:edit" />
              </el-icon>
              <el-icon title="删除会话" @click="deleteConversationTitle(conversation)">
                <Icon icon="ep:delete" />
              </el-icon>
            </span>
          </div>
        </el-row>
      </div>
      <!-- 左底部：工具栏 TODO 芋艿：50% 不太对 -->
      <div class="tool-box">
        <sapn class="w-1/2"> <Icon icon="ep:user" /> 角色仓库 </sapn>
        <sapn class="w-1/2"> <Icon icon="ep:delete" />清空未置顶对话</sapn>
      </div>
    </el-aside>
    <!-- 右侧：会话详情 -->
    <el-container class="detail-container">
      <!-- 右顶部 TODO 芋艿：右对齐 -->
      <el-header class="header">
        <el-button>3.5-turbo-0125 <Icon icon="ep:setting" /></el-button>
        <el-button>
          <Icon icon="ep:user" />
        </el-button>
        <el-button>
          <Icon icon="ep:download" />
        </el-button>
        <el-button>
          <Icon icon="ep:arrow-up" />
        </el-button>
      </el-header>
      <el-main>对话列表
        <div class="chat-list">
          <ChatMessage v-for="(msg, index) in messages" :key="index" :message="msg" />

        </div>

        发送消息框
        <div class="message-container">
          <el-input
            type="textarea"
            v-model="newMessage"
            placeholder="输入消息..."
            class="message-input"
            :rows="3"
          />
          <el-button type="primary" @click="sendMessage">发送</el-button>
        </div>
      </el-main>
      <el-footer/>

      <!-- </el-footer> -->
    </el-container>
  </el-container>
</template>
<script setup lang="ts">
import avatar from '@/assets/imgs/avatar.gif'
import ChatMessage from './chatMessage.vue';

// const chatHistory = ref([])
const messages = ref([
  { id: 1, text: 'Hello', isUser: true },
  { id: 2, text: 'Hi', isUser: false },
  { id: 3, text: 'How are you?', isUser: true },
  { id: 4, text: 'I am fine, thank you.', isUser: false },
  { id: 5, text: 'Goodbye', isUser: true }
])

const conversationList = [
  {
    id: 1,
    title: '历史对话1',
    avatar: avatar
  },
  {
    id: 2,
    title: '历史对话2',
    avatar: avatar
  }
]
const conversationId = ref(1)
const newMessage = ref('')
const searchName = ref('')
const leftHeight = window.innerHeight - 240 // TODO 芋艿：这里还不太对

const changeConversation = (conversation) => {
  console.log(conversation)
  conversationId.value = conversation.id
  // TODO 芋艿：待实现
}

const updateConversationTitle = (conversation) => {
  console.log(conversation)
  // TODO 芋艿：待实现
}

const deleteConversationTitle = (conversation) => {
  console.log(conversation)
  // TODO 芋艿：待实现
}

const searchConversation = () => {
  // TODO 芋艿：待实现
}
const sendMessage = () => {
  if (newMessage.value.trim() === '') {
    return
  }
  messages.value.push({ id: messages.value.length + 1, text: newMessage.value, isUser: true })
  newMessage.value = ''
}

</script>
<style lang="scss" scoped>
.conversation-container {
  .conversation-list {
    .conversation {
      display: flex;
      justify-content: flex-start;
      width: 100%;
      padding: 5px 5px 0 0;
      cursor: pointer;
      &.active {
        // TODO 芋艿：这里不太对
        background-color: #343540;
        .button {
          display: inline;
        }
      }
      .title {
        padding: 5px 10px;
        max-width: 220px;
        font-size: 14px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }
      .avatar {
        width: 28px;
        height: 28px;
        border-radius: 50%;
      }
      .button {
        position: absolute;
        right: 2px;
        top: 16px;
        .el-icon {
          margin-right: 5px;
        }
      }
    }

    .tool-box {
      display: flex;
      justify-content: flex-start;
      padding: 0 20px 10px 20px;
      border-top: 1px solid black;
    }
  }
}

.detail-container {
  .header {
    width: 100%;
    height: 30px;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    padding-top: 10px;
  }
}
.chat-list {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 10px;
  background-color: #fff;
}
.message-container {
  display: flex;
  max-width: 600px;
  height: 100px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 10px;
  background-color: #fff;
}
.send-message-box {
  display: flex;
  align-items: center;
  padding: 10px;
  border-top: 1px solid #ccc;
}
.message-input {
  flex: 1;
  margin-right: 10px;
}

</style>

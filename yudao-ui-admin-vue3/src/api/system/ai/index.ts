import request from '@/config/axios'

export interface UserConversationVO {
                    id: number
                    userId: number
                    topic: string
}

// 查询用户对话列表
export const getUserConversationPage = async (params) => {
  return await request.get({ url: '/system/user-conversation/page', params })
}

// 查询用户对话详情
export const getUserConversation = async (id: number) => {
  return await request.get({ url: '/system/user-conversation/get?id=' + id })
}

// 新增用户对话
export const createUserConversation = async (data: UserConversationVO) => {
  return await request.post({ url: '/system/user-conversation/create', data })
}

// 修改用户对话
export const updateUserConversation = async (data: UserConversationVO) => {
  return await request.put({ url: '/system/user-conversation/update', data })
}

// 删除用户对话
export const deleteUserConversation = async (id: number) => {
  return await request.delete({ url: '/system/user-conversation/delete?id=' + id })
}

// 导出用户对话 Excel
export const exportUserConversationApi = async (params) => {
  return await request.download({ url: '/system/user-conversation/export-excel', params })
}
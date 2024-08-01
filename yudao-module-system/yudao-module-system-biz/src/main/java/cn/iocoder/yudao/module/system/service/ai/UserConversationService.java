package cn.iocoder.yudao.module.system.service.ai;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.ai.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.ai.UserConversationDO;
import cn.iocoder.yudao.module.system.dal.dataobject.ai.ChatItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 用户对话 Service 接口
 *
 * @author donx
 */
public interface UserConversationService {

    /**
     * 创建用户对话
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUserConversation(@Valid UserConversationSaveReqVO createReqVO);

    /**
     * 更新用户对话
     *
     * @param updateReqVO 更新信息
     */
    void updateUserConversation(@Valid UserConversationSaveReqVO updateReqVO);

    /**
     * 删除用户对话
     *
     * @param id 编号
     */
    void deleteUserConversation(Long id);

    /**
     * 获得用户对话
     *
     * @param id 编号
     * @return 用户对话
     */
    UserConversationDO getUserConversation(Long id);

    /**
     * 获得用户对话分页
     *
     * @param pageReqVO 分页查询
     * @return 用户对话分页
     */
    PageResult<UserConversationDO> getUserConversationPage(UserConversationPageReqVO pageReqVO);

    // ==================== 子表（聊天对话内容） ====================

    /**
     * 获得聊天对话内容分页
     *
     * @param pageReqVO 分页查询
     * @param conversationId 对话ID
     * @return 聊天对话内容分页
     */
    PageResult<ChatItemDO> getChatItemPage(PageParam pageReqVO, Long conversationId);

    /**
     * 创建聊天对话内容
     *
     * @param chatItem 创建信息
     * @return 编号
     */
    Long createChatItem(@Valid ChatItemDO chatItem);

    /**
     * 更新聊天对话内容
     *
     * @param chatItem 更新信息
     */
    void updateChatItem(@Valid ChatItemDO chatItem);

    /**
     * 删除聊天对话内容
     *
     * @param id 编号
     */
    void deleteChatItem(Long id);

	/**
	 * 获得聊天对话内容
	 *
	 * @param id 编号
     * @return 聊天对话内容
	 */
    ChatItemDO getChatItem(Long id);

}
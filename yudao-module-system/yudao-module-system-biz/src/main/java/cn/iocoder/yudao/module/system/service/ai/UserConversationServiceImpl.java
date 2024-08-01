package cn.iocoder.yudao.module.system.service.ai;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.ai.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.ai.UserConversationDO;
import cn.iocoder.yudao.module.system.dal.dataobject.ai.ChatItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.system.dal.mysql.ai.UserConversationMapper;
import cn.iocoder.yudao.module.system.dal.mysql.ai.ChatItemMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 用户对话 Service 实现类
 *
 * @author donx
 */
@Service
@Validated
public class UserConversationServiceImpl implements UserConversationService {

    @Resource
    private UserConversationMapper userConversationMapper;
    @Resource
    private ChatItemMapper chatItemMapper;

    @Override
    public Long createUserConversation(UserConversationSaveReqVO createReqVO) {
        // 插入
        UserConversationDO userConversation = BeanUtils.toBean(createReqVO, UserConversationDO.class);
        userConversationMapper.insert(userConversation);
        // 返回
        return userConversation.getId();
    }

    @Override
    public void updateUserConversation(UserConversationSaveReqVO updateReqVO) {
        // 校验存在
        validateUserConversationExists(updateReqVO.getId());
        // 更新
        UserConversationDO updateObj = BeanUtils.toBean(updateReqVO, UserConversationDO.class);
        userConversationMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserConversation(Long id) {
        // 校验存在
        validateUserConversationExists(id);
        // 删除
        userConversationMapper.deleteById(id);

        // 删除子表
        deleteChatItemByConversationId(id);
    }

    private void validateUserConversationExists(Long id) {
        if (userConversationMapper.selectById(id) == null) {
            throw exception(USER_CONVERSATION_NOT_EXISTS);
        }
    }

    @Override
    public UserConversationDO getUserConversation(Long id) {
        return userConversationMapper.selectById(id);
    }

    @Override
    public PageResult<UserConversationDO> getUserConversationPage(UserConversationPageReqVO pageReqVO) {
        return userConversationMapper.selectPage(pageReqVO);
    }

    // ==================== 子表（聊天对话内容） ====================

    @Override
    public PageResult<ChatItemDO> getChatItemPage(PageParam pageReqVO, Long conversationId) {
        return chatItemMapper.selectPage(pageReqVO, conversationId);
    }

    @Override
    public Long createChatItem(ChatItemDO chatItem) {
        chatItemMapper.insert(chatItem);
        return chatItem.getId();
    }

    @Override
    public void updateChatItem(ChatItemDO chatItem) {
        // 校验存在
        validateChatItemExists(chatItem.getId());
        // 更新
        chatItemMapper.updateById(chatItem);
    }

    @Override
    public void deleteChatItem(Long id) {
        // 校验存在
        validateChatItemExists(id);
        // 删除
        chatItemMapper.deleteById(id);
    }

    @Override
    public ChatItemDO getChatItem(Long id) {
        return chatItemMapper.selectById(id);
    }

    private void validateChatItemExists(Long id) {
        if (chatItemMapper.selectById(id) == null) {
            throw exception(CHAT_ITEM_NOT_EXISTS);
        }
    }

    private void deleteChatItemByConversationId(Long conversationId) {
        chatItemMapper.deleteByConversationId(conversationId);
    }

}
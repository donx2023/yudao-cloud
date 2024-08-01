package cn.iocoder.yudao.module.system.dal.mysql.ai;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.ai.ChatItemDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 聊天对话内容 Mapper
 *
 * @author donx
 */
@Mapper
public interface ChatItemMapper extends BaseMapperX<ChatItemDO> {

    default PageResult<ChatItemDO> selectPage(PageParam reqVO, Long conversationId) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChatItemDO>()
            .eq(ChatItemDO::getConversationId, conversationId)
            .orderByDesc(ChatItemDO::getId));
    }

    default int deleteByConversationId(Long conversationId) {
        return delete(ChatItemDO::getConversationId, conversationId);
    }

}
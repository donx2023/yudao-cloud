package cn.iocoder.yudao.module.system.dal.mysql.ai;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.ai.UserConversationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.ai.vo.*;

/**
 * 用户对话 Mapper
 *
 * @author donx
 */
@Mapper
public interface UserConversationMapper extends BaseMapperX<UserConversationDO> {

    default PageResult<UserConversationDO> selectPage(UserConversationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserConversationDO>()
                .eqIfPresent(UserConversationDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(UserConversationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserConversationDO::getId));
    }

}
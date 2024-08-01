package cn.iocoder.yudao.module.system.dal.dataobject.ai;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 用户对话 DO
 *
 * @author donx
 */
@TableName("ai_user_conversation")
@KeySequence("ai_user_conversation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserConversationDO extends BaseDO {

    /**
     * 对话ID
     */
    @TableId
    private Long id;
    
    
    private String topic;
    
    /**
     * 用户ID
     */
    private Long userId;

}
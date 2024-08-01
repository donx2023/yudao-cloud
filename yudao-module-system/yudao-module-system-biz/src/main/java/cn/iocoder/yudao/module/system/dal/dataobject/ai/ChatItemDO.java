package cn.iocoder.yudao.module.system.dal.dataobject.ai;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 聊天对话内容 DO
 *
 * @author donx
 */
@TableName("ai_chat_item")
@KeySequence("ai_chat_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatItemDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 内容
     */
    private String content;
    /**
     * user, bot
     */
    private String type;
    /**
     * 对话ID
     */
    private Long conversationId;

}
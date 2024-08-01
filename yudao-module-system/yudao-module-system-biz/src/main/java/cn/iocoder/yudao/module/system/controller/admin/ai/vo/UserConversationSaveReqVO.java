package cn.iocoder.yudao.module.system.controller.admin.ai.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import cn.iocoder.yudao.module.system.dal.dataobject.ai.ChatItemDO;

@Schema(description = "管理后台 - 用户对话新增/修改 Request VO")
@Data
public class UserConversationSaveReqVO {

    @Schema(description = "对话ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13905")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21896")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

}
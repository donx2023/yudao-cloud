package cn.iocoder.yudao.module.system.controller.admin.ai.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 用户对话 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserConversationRespVO {

    @Schema(description = "对话ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13905")
    @ExcelProperty("对话ID")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21896")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "对话主题", requiredMode = Schema.RequiredMode.REQUIRED, example = "21896")
    @ExcelProperty("主题")
    private String topic;

    @Schema(description = "创建日期")
    @ExcelProperty("创建日期")
    private LocalDateTime createTime;

}
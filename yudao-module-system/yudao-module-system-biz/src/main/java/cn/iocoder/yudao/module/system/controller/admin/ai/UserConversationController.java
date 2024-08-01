package cn.iocoder.yudao.module.system.controller.admin.ai;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.system.controller.admin.ai.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.ai.UserConversationDO;
import cn.iocoder.yudao.module.system.dal.dataobject.ai.ChatItemDO;
import cn.iocoder.yudao.module.system.service.ai.UserConversationService;

@Tag(name = "管理后台 - 用户对话")
@RestController
@RequestMapping("/system/user-conversation")
@Validated
public class UserConversationController {

    @Resource
    private UserConversationService userConversationService;

    @PostMapping("/create")
    @Operation(summary = "创建用户对话")
    @PreAuthorize("@ss.hasPermission('system:user-conversation:create')")
    public CommonResult<Long> createUserConversation(@Valid @RequestBody UserConversationSaveReqVO createReqVO) {
        return success(userConversationService.createUserConversation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户对话")
    @PreAuthorize("@ss.hasPermission('system:user-conversation:update')")
    public CommonResult<Boolean> updateUserConversation(@Valid @RequestBody UserConversationSaveReqVO updateReqVO) {
        userConversationService.updateUserConversation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户对话")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:user-conversation:delete')")
    public CommonResult<Boolean> deleteUserConversation(@RequestParam("id") Long id) {
        userConversationService.deleteUserConversation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户对话")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:user-conversation:query')")
    public CommonResult<UserConversationRespVO> getUserConversation(@RequestParam("id") Long id) {
        UserConversationDO userConversation = userConversationService.getUserConversation(id);
        return success(BeanUtils.toBean(userConversation, UserConversationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用户对话分页")
    @PreAuthorize("@ss.hasPermission('system:user-conversation:query')")
    public CommonResult<PageResult<UserConversationRespVO>> getUserConversationPage(@Valid UserConversationPageReqVO pageReqVO) {
        PageResult<UserConversationDO> pageResult = userConversationService.getUserConversationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UserConversationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出用户对话 Excel")
    @PreAuthorize("@ss.hasPermission('system:user-conversation:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUserConversationExcel(@Valid UserConversationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UserConversationDO> list = userConversationService.getUserConversationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "用户对话.xls", "数据", UserConversationRespVO.class,
                        BeanUtils.toBean(list, UserConversationRespVO.class));
    }

    // ==================== 子表（聊天对话内容） ====================

    @GetMapping("/chat-item/page")
    @Operation(summary = "获得聊天对话内容分页")
    @Parameter(name = "conversationId", description = "对话ID")
    @PreAuthorize("@ss.hasPermission('system:user-conversation:query')")
    public CommonResult<PageResult<ChatItemDO>> getChatItemPage(PageParam pageReqVO,
                                                                                        @RequestParam("conversationId") Long conversationId) {
        return success(userConversationService.getChatItemPage(pageReqVO, conversationId));
    }

    @PostMapping("/chat-item/create")
    @Operation(summary = "创建聊天对话内容")
    @PreAuthorize("@ss.hasPermission('system:user-conversation:create')")
    public CommonResult<Long> createChatItem(@Valid @RequestBody ChatItemDO chatItem) {
        return success(userConversationService.createChatItem(chatItem));
    }

    @PutMapping("/chat-item/update")
    @Operation(summary = "更新聊天对话内容")
    @PreAuthorize("@ss.hasPermission('system:user-conversation:update')")
    public CommonResult<Boolean> updateChatItem(@Valid @RequestBody ChatItemDO chatItem) {
        userConversationService.updateChatItem(chatItem);
        return success(true);
    }

    @DeleteMapping("/chat-item/delete")
    @Parameter(name = "id", description = "编号", required = true)
    @Operation(summary = "删除聊天对话内容")
    @PreAuthorize("@ss.hasPermission('system:user-conversation:delete')")
    public CommonResult<Boolean> deleteChatItem(@RequestParam("id") Long id) {
        userConversationService.deleteChatItem(id);
        return success(true);
    }

	@GetMapping("/chat-item/get")
	@Operation(summary = "获得聊天对话内容")
	@Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:user-conversation:query')")
	public CommonResult<ChatItemDO> getChatItem(@RequestParam("id") Long id) {
	    return success(userConversationService.getChatItem(id));
	}

}
package com.ruoyi.device.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.device.domain.DeviceBorrow;
import com.ruoyi.device.service.IDeviceBorrowService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 借用信息Controller
 *
 * @author ruoyi
 * @date 2025-05-07
 */
@RestController
@RequestMapping("/device/borrow")
public class DeviceBorrowController extends BaseController
{
    @Autowired
    private IDeviceBorrowService deviceBorrowService;

    /**
     * 查询借用信息列表
     */
    @PreAuthorize("@ss.hasPermi('device:borrow:list')")
    @GetMapping("/list")
    public TableDataInfo list(DeviceBorrow deviceBorrow)
    {
        startPage();
        List<DeviceBorrow> list = deviceBorrowService.selectDeviceBorrowList(deviceBorrow);
        return getDataTable(list);
    }

    /**
     * 导出借用信息列表
     */
    @PreAuthorize("@ss.hasPermi('device:borrow:export')")
    @Log(title = "借用信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceBorrow deviceBorrow)
    {
        List<DeviceBorrow> list = deviceBorrowService.selectDeviceBorrowList(deviceBorrow);
        ExcelUtil<DeviceBorrow> util = new ExcelUtil<DeviceBorrow>(DeviceBorrow.class);
        util.exportExcel(response, list, "借用信息数据");
    }

    /**
     * 获取借用信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('device:borrow:query')")
    @GetMapping(value = "/{borrowId}")
    public AjaxResult getInfo(@PathVariable("borrowId") Long borrowId)
    {
        return success(deviceBorrowService.selectDeviceBorrowByBorrowId(borrowId));
    }

    /**
     * 新增借用信息
     */
    @PreAuthorize("@ss.hasPermi('device:borrow:add')")
    @Log(title = "借用信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceBorrow deviceBorrow)
    {
        return toAjax(deviceBorrowService.insertDeviceBorrow(deviceBorrow));
    }

    /**
     * 修改借用信息
     */
    @PreAuthorize("@ss.hasPermi('device:borrow:edit')")
    @Log(title = "借用信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceBorrow deviceBorrow)
    {
        return toAjax(deviceBorrowService.updateDeviceBorrow(deviceBorrow));
    }

    /**
     * 删除借用信息
     */
    @PreAuthorize("@ss.hasPermi('device:borrow:remove')")
    @Log(title = "借用信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{borrowIds}")
    public AjaxResult remove(@PathVariable Long[] borrowIds)
    {
        return toAjax(deviceBorrowService.deleteDeviceBorrowByBorrowIds(borrowIds));
    }
}

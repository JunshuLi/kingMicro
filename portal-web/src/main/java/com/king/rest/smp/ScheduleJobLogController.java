package com.king.rest.smp;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.king.api.smp.ScheduleJobService;
import com.king.common.utils.JsonResponse;
import com.king.common.utils.Page;
import com.king.dal.gen.model.Response;
import com.king.dal.gen.model.smp.ScheduleJobLog;
import com.king.utils.Query;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 定时任务日志
 * @author King chen
 * @email 396885563@qq.com
 * @date 2017年12月29日
 */
@RestController
@Api(value = "定时任务日志", description = "定时任务日志")
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
	@Autowired
	private ScheduleJobService scheduleJobService;
	/**
	 * 定时任务日志列表
	 */
	@ApiOperation(value = "定时任务日志列表",response=Response.class, notes = "权限编码（sys:schedule:log）")
	@GetMapping("/list")
	@RequiresPermissions("sys:schedule:log")
	public JsonResponse list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params,ScheduleJobLog.class.getSimpleName());	
		Page page = scheduleJobService.getPageScheduleJobLog(query);
		return JsonResponse.success(page);
	}
	
	/**
	 * 定时任务日志信息
	 */
	@ApiOperation(value = "定时任务日志信息",response=Response.class,notes = "权限编码（sys:scheduleLog:list）")
	@GetMapping("/info/{logId}")
	@RequiresPermissions("sys:scheduleLog:list")
	public JsonResponse info(@PathVariable("logId") Object logId){
		ScheduleJobLog log = scheduleJobService.queryScheduleJobLog(logId);
		
		return JsonResponse.success(log);
	}
}

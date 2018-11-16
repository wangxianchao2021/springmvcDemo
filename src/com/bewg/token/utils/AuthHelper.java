package com.bewg.token.utils;

import com.alibaba.fastjson.JSONObject;
import com.bewg.token.entity.DingDingEnv;
import com.bewg.token.exception.OApiException;
import com.dingtalk.open.client.ServiceFactory;
import com.dingtalk.open.client.api.model.corp.JsapiTicket;
import com.dingtalk.open.client.api.service.corp.CorpConnectionService;
import com.dingtalk.open.client.api.service.corp.JsapiService;
import com.dingtalk.open.client.common.SdkInitException;
import com.dingtalk.open.client.common.ServiceException;
import com.dingtalk.open.client.common.ServiceNotExistException;

public class AuthHelper {

	// 调整到1小时50分钟
	private static final long cacheTime = 1000 * 60 * 55 * 2;

	/*
	 * 在此方法中，为了避免频繁获取access_token，
	 * 在距离上一次获取access_token时间在两个小时之内的情况，
	 * 将直接从持久化存储中读取access_token
	 * 
	 * 因为access_token和jsapi_ticket的过期时间都是7200秒
	 * 所以在获取access_token的同时也去获取了jsapi_ticket
	 * 注：jsapi_ticket是在前端页面JSAPI做权限验证配置的时候需要使用的
	 * 具体信息请查看开发者文档--权限验证配置
	 */
	public static String getAccessToken() throws OApiException {
		
		String accToken = "";
		
		try {
			
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			
	        CorpConnectionService corpConnectionService = serviceFactory.getOpenService(CorpConnectionService.class);
	        
	        accToken = corpConnectionService.getCorpToken(DingDingEnv.CORP_ID, DingDingEnv.CORP_SECRET);
        
		} catch (SdkInitException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ServiceNotExistException e) {
			e.printStackTrace();
		}
		
		return accToken;
	}

	// 正常的情况下，jsapi_ticket的有效期为7200秒，所以开发者需要在某个地方设计一个定时器，定期去更新jsapi_ticket
	public static String getJsapiTicket(String accessToken) throws OApiException {
		
		JSONObject jsTicketValue = (JSONObject) FileUtils.getValue("jsticket", DingDingEnv.CORP_ID);
		
		long curTime = System.currentTimeMillis();
		
		String jsTicket = "";

		 if (jsTicketValue == null || curTime - jsTicketValue.getLong("begin_time") >= cacheTime) {
			 
			ServiceFactory serviceFactory;
			
			try {
				
				serviceFactory = ServiceFactory.getInstance();
				JsapiService jsapiService = serviceFactory.getOpenService(JsapiService.class);

				JsapiTicket JsapiTicket = jsapiService.getJsapiTicket(accessToken, "jsapi");
				jsTicket = JsapiTicket.getTicket();

				JSONObject jsonTicket = new JSONObject();
				JSONObject jsontemp = new JSONObject();
				jsontemp.clear();
				jsontemp.put("ticket", jsTicket);
				jsontemp.put("begin_time", curTime);
				jsonTicket.put(DingDingEnv.CORP_ID, jsontemp);
				FileUtils.write2File(jsonTicket, "jsticket");
				
			} catch (SdkInitException e) {
				e.printStackTrace();
			} catch (ServiceException e) {
				e.printStackTrace();
			} catch (ServiceNotExistException e) {
				e.printStackTrace();
			}
			return jsTicket;
		 } else {
			 return jsTicketValue.getString("ticket");
		 }
	}
}

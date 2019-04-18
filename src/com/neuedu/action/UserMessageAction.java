package com.neuedu.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.model.UserMessage;
import com.neuedu.model.Users;
import com.neuedu.service.UserMessageService;

@Controller
@RequestMapping(value = "/usermessage")
public class UserMessageAction {

	@Autowired
	private UserMessageService ums;
	
	@RequestMapping(value = "/findAll")
	public void findAll(HttpServletRequest request,HttpServletResponse response)throws Exception{
		int page=Integer.parseInt(request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows"));
		
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> pageMap=new HashMap<String, Object>();
		
		pageMap.put("startPage", (page-1)*rows);
		pageMap.put("endPage", rows);
		
		List<UserMessage> u= ums.getAll(pageMap);
		int total=ums.countUserMessage();
		map.put("rows", u);
		map.put("total", total);
		String str=JSONObject.toJSONString(map);
		response.getWriter().write(str);
	}
	
	
	@RequestMapping(value="addUserMessage")
	public void addUserMessage(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String userName = request.getParameter("userName");
		String nickName = request.getParameter("nickName");
		String password = request.getParameter("password");
		int userPhone = Integer.parseInt(request.getParameter("userPhone")); 
		String userEmail = request.getParameter("userEmail");
		String userDate = request.getParameter("userDate");
		
		UserMessage u = new UserMessage();
		u.setUserName(userName);
		u.setNickName(nickName);
		u.setPassword(password);
		u.setUserPhone(userPhone);
		u.setUserEmail(userEmail);
		u.setUserDate(userDate);
		
		int res = ums.addUserMessage(u);
		System.out.println(res);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(res>0){
			map.put("success", true);
		}else{
			map.put("success", false);
			map.put("errorMsg", "Save usermessage fail !!!");
		}		
		String str=JSONObject.toJSONString(map);
		response.getWriter().write(str);		
		
		
	}	
	
	
	
	@RequestMapping(value="updateUserMessage")
	public void updateUserMessage(HttpServletRequest request,HttpServletResponse response) throws Exception{
	
		String userName = request.getParameter("userName");
		String nickName = request.getParameter("nickName");
		String password = request.getParameter("password");
		int userPhone = Integer.parseInt(request.getParameter("userPhone")); 
		String userEmail = request.getParameter("userEmail");
		String userDate = request.getParameter("userDate");
		
		UserMessage u = new UserMessage();
		u.setUserName(userName);
		u.setNickName(nickName);
		u.setPassword(password);
		u.setUserPhone(userPhone);
		u.setUserEmail(userEmail);
		u.setUserDate(userDate);		
		
		int res = ums.updateUserMessage(u);
		Map<String, Object> map=new HashMap<String, Object>();
		if(res>0){
			map.put("success", true);
		}else{
			map.put("success", false);
			map.put("errorMsg", "update UserMessage fail !!!");
		}
		String str=JSONObject.toJSONString(map);
		response.getWriter().write(str);		
		
	}	

	
	@RequestMapping(value="deleteUserMessage")
	public void deleteUserMessage(HttpServletRequest request,HttpServletResponse response) throws Exception{

		int id=Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		
		int res = ums.deleteUserMessage(id);
		
		Map<String, Object> map=new HashMap<String, Object>();
		if(res>0){
			map.put("success", true);
		}else{
			map.put("success", false);
			map.put("errorMsg", "delete UserMessage fail !!!");
		}
		String str=JSONObject.toJSONString(map);
		response.getWriter().write(str);
		
	}	
	
	
	
}

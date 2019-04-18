package com.neuedu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neuedu.dao.UserDao;
import com.neuedu.dao.UserMessageDao;
import com.neuedu.model.UserMessage;
import com.neuedu.model.Users;
import com.neuedu.service.UserMessageService;
@Service
public class UserMessageImpl implements UserMessageService {

	@Autowired
	private UserMessageDao umd;

	public void setUserMessageDao(UserMessageDao umd) {
		this.umd = umd;
	}	
	
	
	
	
	@Override
	public List<UserMessage> getAll(Map<String, Object> map) {
		// TODO �Զ����ɵķ������
		return umd.findAll(map);
	}

	@Override
	public int countUserMessage() {
		// TODO �Զ����ɵķ������
		return umd.countUserMessage();
	}

	@Override
	public int addUserMessage(UserMessage u) {
		// TODO �Զ����ɵķ������
		return umd.addUserMessage(u);
	}

	@Override
	public int updateUserMessage(UserMessage u) {
		// TODO �Զ����ɵķ������
		return umd.updateUserMessage(u);
	}

	@Override
	public int deleteUserMessage(int id) {
		// TODO �Զ����ɵķ������
		return umd.deleteUserMessage(id);
	}

}

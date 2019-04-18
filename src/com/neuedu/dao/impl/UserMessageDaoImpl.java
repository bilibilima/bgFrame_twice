package com.neuedu.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neuedu.dao.UserDao;
import com.neuedu.dao.UserMessageDao;
import com.neuedu.model.UserMessage;
/**
 * spring����dao�ӿ�ʵ���࣬����spring���й���
 */
@Repository
public class UserMessageDaoImpl implements UserMessageDao {
	
	/**
	 * ����ע�룬����㼶������spring+mybatis
	 */
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	
	@Override
	public int countUserMessage() {
		// TODO �Զ����ɵķ������
		return sqlSessionTemplate.getMapper(UserMessageDao.class).countUserMessage();
	}

	@Override
	public List<UserMessage> findAll(Map<String, Object> map) {
		// TODO �Զ����ɵķ������
		return sqlSessionTemplate.getMapper(UserMessageDao.class).findAll(map);
	}

	@Override
	public int addUserMessage(UserMessage u) {
		// TODO �Զ����ɵķ������
		return sqlSessionTemplate.getMapper(UserMessageDao.class).addUserMessage(u);
	}

	@Override
	public int updateUserMessage(UserMessage u) {
		// TODO �Զ����ɵķ������
		return sqlSessionTemplate.getMapper(UserMessageDao.class).updateUserMessage(u);
	}

	@Override
	public int deleteUserMessage(int id) {
		// TODO �Զ����ɵķ������
		return sqlSessionTemplate.getMapper(UserMessageDao.class).deleteUserMessage(id);
	}

}

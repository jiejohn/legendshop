package com.done.struts;

import javax.servlet.http.*;
import org.apache.log4j.Logger;
import bingosoft.jcf.util.TimerUtil;


import java.util.*;

public class OnLineUser implements HttpSessionBindingListener {
	private static Logger logger = Logger.getLogger(OnLineUser.class);
	private Vector users = new Vector();
	
	public OnLineUser() {
		
	}

	public int getCount() {
		users.trimToSize();
		return users.capacity();
	}

	public boolean existUser(String userName) {
		users.trimToSize();
		boolean existUser = false;
		for (int i = 0; i < users.capacity(); i++) {
			if (userName.equals((String) users.get(i))) {
				existUser = true;
				break;
			}
		}
		return existUser;
	}

	public boolean deleteUser(String userName) {
		users.trimToSize();
		if (existUser(userName)) {
			int currUserIndex = -1;
			for (int i = 0; i < users.capacity(); i++) {
				if (userName.equals((String) users.get(i))) {
					currUserIndex = i;
					break;
				}
			}
			if (currUserIndex != -1) {
				users.remove(currUserIndex);
				users.trimToSize();
				return true;
			}
		}
		return false;
	}

	public Vector getOnLineUser() {
		return users;
	}

	public void valueBound(HttpSessionBindingEvent e) {
		users.trimToSize();
		if (!existUser(e.getName())) {
			users.add(e.getName());
			logger.info(e.getName() + "\t      登入到系统\t" + (TimerUtil.getStrDate())+"\t在线用户数为：" + getCount());
		} else
			logger.info(e.getName() + "已经存在");
	}

	public void valueUnbound(HttpSessionBindingEvent e) {
		users.trimToSize();
		String userName = e.getName();
		deleteUser(userName);
		logger.info(userName + "\t      退出系统\t" + (TimerUtil.getStrDate())+"\t在线用户数为：" + getCount());
	}
}
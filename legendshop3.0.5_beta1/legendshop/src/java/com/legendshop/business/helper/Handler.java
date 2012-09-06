package com.legendshop.business.helper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Handler {

	void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;

}

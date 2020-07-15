/*
 * Copyright (c) 2012-2016 by Zalo Group.
 * All Rights Reserved.
 */
package com.vng.zing.weedapp.model;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * @Note: Class model xử lý business chính cho mỗi loại handler/controller ,
 * được thiết kế theo pattern Singleton Object, thiết kế kiểu này cho phép các
 * Model object truy xuất được lẫn nhau (cùng package nên truy xuất được các
 * thuộc tính protected của nhau), có thể bổ sung thiết kế bằng cách tạo ra 1
 * BaseModel xử lý các hàm tiện ích chung, các Biz Model khác thừa kế từ đó
 *
 * @author namnq
 */
public class TrackModel  {

	private static final Logger _Logger = Logger.getLogger(TrackModel.class);
	public static final TrackModel Instance = new TrackModel();

	private TrackModel() {
	}

	public void process(HttpServletRequest req, HttpServletResponse resp) {

		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = resp.getWriter();

			out.println("You are accessing /track");
		} catch (Exception ex) {
			_Logger.error(null, ex);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}

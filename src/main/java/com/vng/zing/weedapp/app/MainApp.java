/*
 * Copyright (c) 2012-2016 by Zalo Group.
 * All Rights Reserved.
 */
package com.vng.zing.weedapp.app;

import com.vng.zing.weedapp.handlers.TrackHandler;
import com.vng.zing.weedapp.servers.HServers;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 *
 * @author namnq
 */
public class MainApp {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws Exception {

		Server server = new Server(8080);
		ServletHandler context = new ServletHandler();

		context.addServletWithMapping(TrackHandler.class, "/track");


		server.setHandler(context);
		server.start();
		server.join();



	}
}

package com.portscanner.business;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PortScannerBusiness {
	
	public int getAmountOfOpenPorts() throws InterruptedException, ExecutionException {
		final ExecutorService executorService = Executors.newFixedThreadPool(20);
		
		// localhost
		final String ip = "127.0.0.1";
		final int timeout = 200;
		final List<Future<Boolean>> futures = new ArrayList<>();
		
		for (int porta = 1; porta <= 65535; porta++) {
			futures.add(isPortOpen(executorService, ip, porta, timeout));
		}
		
		executorService.shutdown();
		int openPorts = 0;
		
		for (final Future<Boolean> future : futures) {
			if (future.get()) {
				openPorts++;
			}
		}
		return openPorts;
	}

	private Future<Boolean> isPortOpen(ExecutorService executorService, String ip, int port, int timeout) {
		return executorService.submit(new Callable<Boolean>() {
			@Override
			public Boolean call() {
				try {
					Socket socket = new Socket();
					socket.connect(new InetSocketAddress(ip, port), timeout);
					socket.close();
					return true;
					
				} catch (Exception ex) {
					return false;
				}
			}
		});
	}

}

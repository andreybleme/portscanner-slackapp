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

import org.springframework.stereotype.Component;

import com.portscanner.dto.OpenPortDTO;

@Component
public class PortScannerBusinessImpl implements PortScannerBusiness {
	
	private final ExecutorService executorService = Executors.newFixedThreadPool(20);
	private static final int TIMEOUT = 200;
	
	public OpenPortDTO getOpenPorts(String ip) throws InterruptedException, ExecutionException {
		final List<Future<Boolean>> futures = new ArrayList<>();
		
		for (int porta = 1; porta <= 65535; porta++) {
			futures.add(isPortOpen(executorService, ip, porta, TIMEOUT));
		}
		
		executorService.shutdown();
		
		int amountOfOpenPorts = 0;
		int openPort = 0;
		List<String> openPorts = new ArrayList<>();
		
		for (final Future<Boolean> future : futures) {
			openPort++;

			if (future.get()) {
				openPorts.add(Integer.toString(openPort));
				amountOfOpenPorts++;	
			}
		}
		return new OpenPortDTO(amountOfOpenPorts, openPorts);
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

package grpcservices;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import grpcservices.computerService;

public class computerServer {

	public static void main(String[] args) {
		
		
		
		
		Server server = ServerBuilder.forPort(9090).addService(new computerService()).build();
		
		server.start();
		
		System.out.println("Server started at" + server.getPort());
		
		server.awaitTermination();
		
	}
	
}

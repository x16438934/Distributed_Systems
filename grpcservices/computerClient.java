package grpcservices;

import com.dsproject.grpcservices.APIResponse;
import com.dsproject.grpcservices.LoginRequest;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class computerClient {

public static void main(String[] args) {
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",9090).usePlaintext().build();
		
		
		
		
		
		LoginRequest loginrequest = LoginRequest.newBuilder().setUsername("RAMasdf").setPassword("RAM").build();
		
		APIResponse response = computerService.login(loginrequest);
		
		System.out.println(response.getResponsemessage());
		
		
	}
	
}

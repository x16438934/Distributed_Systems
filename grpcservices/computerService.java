package grpcservices;

public class computerService extends computer {

	//when client calls the login api it will come here
	public void login(LoginRequest request, StreamObserver<APIResponse> responseObserver) {
		System.out.println("Inside login");
		
		//getting data from request
		String username =  request.getUsername();
		String password =  request.getPassword();
		
		//construct object in for response
	APIResponse.Builder response = APIResponse.newBuilder();
	if(username.equals(password)) {
	//return success case	
	
		response.setResponseCode(0).setResponsemessage("Success");
	}
	else {
		// return failure case
		
		response.setResponseCode(100).setResponsemessage("Invalid password");	
	}
	
	responseObserver.onNext(response.build());
	responseObserver.onCompleted();
	}
	
	public void logout(Empty request, StreamObserver<APIResponse> responseObserver) {
	
	}
	
}

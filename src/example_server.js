var PROTO_PATH = __dirname + '/example.proto';

var grpc = require('grpc');
var protoLoader = require('@grpc/proto-loader');

var packageDefinition = protoLoader.loadSync(PROTO_PATH, { keepCase: true, longs: String, enums: String, defaults: true, oneofs: true });

var example_proto = grpc.loadPackageDefinition(packageDefinition);

function responeWithHello(call, callback){
    callback(null, {message: `Hello ${call.request.name}`});
}

function responeWithHelloStream(call){
    var num_request  = call.request.num_response;

    for(var i=0; i<num_request; i++){
        call.write({message: `Hello ${call.request.name} ${i}`})
    }

    call.end();
}


function startServer(){
    var server = new grpc.Server();
    server.addService(example_proto.Example.service, {
        responeWithHello: responeWithHello,
        responeWithHelloStream: responeWithHelloStream
    });

    server.bind('0.0.0.0:8001', grpc.ServerCredentials.createInsecure());
    server.start();
}


startServer();

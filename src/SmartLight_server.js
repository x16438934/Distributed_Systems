var PROTO_PATH = __dirname + '/example.proto';

var grpc = require('grpc');
var protoLoader = require('@grpc/proto-loader');

var packageDefinition = protoLoader.loadSync(PROTO_PATH, { keepCase: true, longs: String, enums: String, defaults: true, oneofs: true });

var example_proto = grpc.loadPackageDefinition(packageDefinition);

function responeWithLight(call, callback){
    callback(null, {message: `The colour is ${call.request.name}`});
}

function responeWithLightStream(call){
    var num_request  = call.request.num_response;

    for(var i=0; i<num_request; i++){
        call.write({message: `The colour is ${call.request.name} ${i}`})
    }

    call.end();
}


function startServer(){
    var server = new grpc.Server();
    server.addService(example_proto.Example.service, {
        responeWithLight: responeWithLight,
        responeWithLightStream: responeWithLightStream
    });

    server.bind('0.0.0.0:8001', grpc.ServerCredentials.createInsecure());
    server.start();
}


startServer();

var PROTO_PATH = __dirname + '/example.proto';

var grpc = require('grpc');
var protoLoader = require('@grpc/proto-loader');

var packageDefinition = protoLoader.loadSync(PROTO_PATH, { keepCase: true, longs: String, enums: String, defaults: true, oneofs: true });

var example_proto = grpc.loadPackageDefinition(packageDefinition);

function configureClient(){
    var client = new example_proto.Example('localhost:8001', grpc.credentials.createInsecure());

    // client.responeWithHello({name: 'Dave'}, function(err, response){
    //     console.log(`Response: ${response.message}`);
    // });

    var call = client.responeWithHelloStream({name: 'Dave', num_response: 5});

    call.on('data', function(response){
        console.log(`Response: ${response.message}`);
    });

    call.on('end', function(){
        console.log('end');
    });
}

configureClient();
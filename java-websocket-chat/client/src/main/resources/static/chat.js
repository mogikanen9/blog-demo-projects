function send(){
    var exampleSocket = new WebSocket("ws://localhost:8082/chat/{username}");
    exampleSocket.onopen = function (event) {
        exampleSocket.send("Here's some text that the server is urgently awaiting!");
        alert('done');
      };
}
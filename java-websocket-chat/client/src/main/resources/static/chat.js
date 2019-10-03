var exampleSocket;

function connect(){

    exampleSocket = new WebSocket("ws://localhost:8082/chat/{username}");

    exampleSocket.onopen = function (event) {
        exampleSocket.send("Connection request from client23...");
        console.log('initiating connection to server...');
      };

      exampleSocket.onmessage = function (event){
        console.log('server replied with->'+event.data);
        var chatMessages = document.getElementById('chatMessages');
        chatMessages.value =  chatMessages.value +'\n'+event.data;
      };
}

function send(data){
 
    if(exampleSocket && exampleSocket.readyState=='1'){//OPEN
        exampleSocket.send(data);
        console.log('data message sent to server');
    }
    
}

connect();
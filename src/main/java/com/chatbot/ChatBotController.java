package com.chatbot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;  
@RestController  
public class ChatBotController {
	@Autowired
	private ChatBotService chatBotService;
	@RequestMapping("/hello")  
    public String hello(){  
        return"Hello!";  
    }  
	
	@RequestMapping(value="/data", method=RequestMethod.POST)  
    public Financial getData(@RequestBody ChatBotQuery chatBotQuery){  
        return chatBotService.getData(chatBotQuery);  
    }  
}

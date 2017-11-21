package com.chatbot;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service 
public class ChatBotService {
public Financial getData(ChatBotQuery chatBotQuery) {
	Map<String, List<Map<String, Financial>>> parentMap = ReadExcel.readFile(chatBotQuery.getFileType());
	Financial fin = ReadExcel.getData(chatBotQuery.getDuName(), chatBotQuery.getSheetType(),chatBotQuery.getDataType(), parentMap);
	return fin;
}
}

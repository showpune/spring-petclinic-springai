package org.springframework.samples.petclinic.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;


@Component
public class Agent {

	@Autowired
	private ChatClient chatClient;

	@Autowired
	private VectorStore vectorStore;

	public String chat(String userMessage, String username) {
		List<Document> docs = vectorStore.similaritySearch(userMessage);
		StringBuffer sop = new StringBuffer();
		for (Document doc : docs) {
			sop.append(doc.getContent()).append("\n");
		}

		Consumer<ChatClient.AdvisorSpec> advisorSpecConsumer = advisorSpec -> {
			advisorSpec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, username);
		};
		return chatClient
			.prompt()
			.advisors(advisorSpecConsumer)
			.user(userMessage + "\n" + sop)
			.system("You are a customer support agent of a pet clinic. You will answer question from a petclinic customer."
				+ "you will always answer the customer question according to Pet clinic Terms of Use \n"
				+ "The customer last name is " + username)
			.functions("queryOwners")
			.call()
			.content();
	}

}

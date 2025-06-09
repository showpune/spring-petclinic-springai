package org.springframework.samples.petclinic.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Component
public class Agent {

	private static final String TRANSLATE = "Generate 1 different versions of a provided user query. "
			+ "but they should all retain the original meaning. "
			+ "It will be used to retrieve relevant documents from a English document. "
			+ "Without enumerations, hyphens, or any additional formatting!";

	@Autowired
	private ChatClient chatClient;

	@Value("classpath:/prompts/system-message.st")
	private Resource systemResource;

	public String chat(String userMessage, String username) {

		try {
			PromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemResource);
			Map<String, Object> systemParameters = new HashMap<>() {
				{
					put("username", username);
				}
			};

			return chatClient.prompt()
				.system(systemPromptTemplate.render(systemParameters))
				.user(userMessage)
				.call()
				.content();
		}
		catch (Exception e) {
			return "Sorry, I am not able to help you with that.";
		}
	}

}

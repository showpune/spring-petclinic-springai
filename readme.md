# Spring PetClinic Sample Application With Azure OpenAI and springAi

## Understanding the Spring Petclinic application with a few diagrams

[See the presentation here](https://speakerdeck.com/michaelisvy/spring-petclinic-sample-application)

## Run Petclinic locally

Spring Petclinic is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built
using [Maven](https://spring.io/guides/gs/maven/). You can build a jar
file and run it from the command line (it should work just as well with Java 17 or newer), Before you build the
application, you need to setup some openai properties in `src/main/resources/application.properties`:

```bash
git clone https://github.com/showpune/spring-petclinic-springai.git
cd spring-petclinic-springai
mv src/main/resources/application.properties.example src/main/resources/application.properties
```

edit the `src/main/resources/application.properties` file and add the following properties:

```properties
spring.ai.azure.openai.api-key=**
spring.ai.azure.openai.endpoint=https://***.openai.azure.com/
spring.ai.azure.openai.chat.options.deployment-name=gpt-4
spring.ai.azure.openai.chat.options.temperature=0.8
spring.ai.chat.client.enabled=true
spring.ai.azure.openai.embedding.options.deployment-name=text-embedding-ada-002
```

You can build the application by running the following command:

```bash
./mvnw package
java -jar target/*.jar
```

You can then access the Petclinic at <http://localhost:8080/>
<img width="1042" alt="petclinic-screenshot" src="https://github.com/showpune/spring-petclinic-langchain4j/assets/1787505/52878caa-8bdd-48c4-a2e7-193f68054c3e">

And the OpenAI chatbot at <http://localhost:8080/chat.html>.
<img width="1042" alt="petclinic-screenshot" src="https://github.com/showpune/spring-petclinic-langchain4j/assets/1787505/11caef70-6411-4e72-9ae9-4902fb8ac96b">

## Workthrough of the chat agent
Go to the owner page, you can query all owner information of "Davis"
![image](https://github.com/showpune/spring-petclinic-springai/assets/1787505/ab3f7f10-a006-46c6-a363-637ea538bd5e)


You can also talk with the agent, query your information.
![image](https://github.com/showpune/spring-petclinic-springai/assets/1787505/2591e97b-aee8-4dfc-85d8-5b62691a2c13)

you can also talk with Chinese
![image](https://github.com/showpune/spring-petclinic-springai/assets/1787505/4a020ba8-5768-424b-b2e6-09812ecf9534)

Go to the owner page you can see your information is added
![image](https://github.com/showpune/spring-petclinic-springai/assets/1787505/b74b383e-680f-4e7d-ac1c-eaa32bd59c4e)

## License

The Spring PetClinic sample application is released under version 2.0 of
the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).

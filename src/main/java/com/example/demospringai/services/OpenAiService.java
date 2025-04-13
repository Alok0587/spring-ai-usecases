package com.example.demospringai.services;

import com.example.demospringai.text.prompttemplate.CountryCuisines;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OpenAiService {

	private ChatClient chatClient;

    @Autowired
    private EmbeddingModel embeddingModel;

    @Autowired
    private VectorStore vectorStore;

    public OpenAiService(ChatClient.Builder builder) {
        //Adding advisor to keep previous chat records for answering
        this.chatClient = builder.defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory())).build();
    }
    public ChatResponse generateAnswer(String question) {
        //OpenAiChatOptions options= new OpenAiChatOptions();
        /*
        Here to have more control on response
         */
//        options.setTemperature(0.7);
//        options.setMaxTokens(20);
        return chatClient.prompt(new Prompt(question)).call().chatResponse();
    }


    public String getTravelGuidance(String city, String month, String language, String budget) {
        PromptTemplate promptTemplate= new PromptTemplate("Welcome to the {city} travel guide!\n" +
                "If you're visiting in {month}, here's what you can do:\n" +
                "1. Must-visit attractions.\n" +
                "2. Local cuisine you must try.\n" +
                "3. Useful phrases in {language}.\n" +
                "4. Tips for traveling on a {budget} budget.\n" +
                "Enjoy your trip!");
        Prompt prompt= promptTemplate.create(Map.of("city",city,"month",month,"language",language,"budget",budget));
        return chatClient.prompt(prompt).call().chatResponse().getResult().getOutput().getText();

    }

    public CountryCuisines getCuisines(String country, String numCuisines, String language) {
        PromptTemplate promptTemplate = new PromptTemplate("You are an expert in traditional cuisines.\n" +
                "You provide information about a specific dish from a specific\n" +
                "country.\n" +
                "Answer the question: What is the traditional cuisine of {country}?" +
                "Return a list of {numCuisines} in {language}" +
                "Avoid giving information about fictional places. If the country is\n" +
                "fictional\n" +
                "or non-existent answer: I don't know.");
        Prompt prompt = promptTemplate.create(Map.of("country", country, "numCuisines", numCuisines, "language", language));

        return chatClient.prompt(prompt).call().entity(CountryCuisines.class);
    }
    /*
    if you are running some model locally then we may need to download embedding model separately
    you can use the command like- "ollama pull ollama pull mxbai-embed-large"
     */

    public float[] embed(String text){
        return embeddingModel.embed(text);
    }

    public Double findSimilarity(String text1, String text2) {
        List<float[]> similarityVector= embeddingModel.embed(List.of(text1, text2));
        return cosineSimilarity(similarityVector.get(0),similarityVector.get(1));

    }
    private double cosineSimilarity(float[] vectorA, float[] vectorB) {
        if (vectorA.length != vectorB.length) {
            throw new IllegalArgumentException("Vectors must be of the same length");
        }

        // Initialize variables for dot product and magnitudes
        double dotProduct = 0.0;
        double magnitudeA = 0.0;
        double magnitudeB = 0.0;

        // Calculate dot product and magnitudes
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            magnitudeA += vectorA[i] * vectorA[i];
            magnitudeB += vectorB[i] * vectorB[i];
        }

        // Calculate and return cosine similarity
        return dotProduct / (Math.sqrt(magnitudeA) * Math.sqrt(magnitudeB));
    }

    public List<Document> searchJobs(String query) {
        return vectorStore.similaritySearch(SearchRequest.builder().query(query).topK(3).build());
    }

}

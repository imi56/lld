package com.practice.polling;
import java.util.*;
import java.util.concurrent.*;


public class PollDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        PollManager manager = new PollManager();

        // Create a new poll
        String pollId = manager.createPoll("What is your favorite programming language?",
                                           Arrays.asList("Java", "Python", "C++", "JavaScript"));

        System.out.println("Poll created with ID: " + pollId);

        // Simulate multiple users voting concurrently
        Future<Boolean> user1 = manager.voteOnPollAsync(pollId, "Java");
        Future<Boolean> user2 = manager.voteOnPollAsync(pollId, "Python");
        Future<Boolean> user3 = manager.voteOnPollAsync(pollId, "Java");
        Future<Boolean> user4 = manager.voteOnPollAsync(pollId, "JavaScript");
        Future<Boolean> user5 = manager.voteOnPollAsync(pollId, "C++");

        // Wait for all votes to complete
        System.out.println("User 1 vote successful: " + user1.get());
        System.out.println("User 2 vote successful: " + user2.get());
        System.out.println("User 3 vote successful: " + user3.get());
        System.out.println("User 4 vote successful: " + user4.get());
        System.out.println("User 5 vote successful: " + user5.get());

        // Close the poll
        manager.closePoll(pollId);

        // Retrieve and print poll results
        Map<String, Integer> results = manager.getPollResults(pollId);

        System.out.println("Poll Results:");
        results.forEach((option, votes) -> System.out.println(option + ": " + votes));

        // Shutdown the thread pool
        manager.shutdown();
    }
}


package com.practice.polling;

import java.util.*;
import java.util.concurrent.*;

class PollManager {
    private final ConcurrentHashMap<String, Poll> pollStore; // Thread-safe poll storage
    private final ExecutorService executorService; // Thread pool for concurrent tasks

    public PollManager() {
        this.pollStore = new ConcurrentHashMap<>();
        this.executorService = Executors.newCachedThreadPool(); // Dynamically resizes threads
    }

    public String createPoll(String question, List<String> options) {
        String pollId = UUID.randomUUID().toString();
        Poll poll = new Poll(pollId, question, options);
        pollStore.put(pollId, poll); // Thread-safe insertion
        return pollId;
    }

    public boolean deletePoll(String pollId) {
        return pollStore.remove(pollId) != null; // Thread-safe removal
    }

    public boolean updatePoll(String pollId, String newQuestion) {
        Poll poll = pollStore.get(pollId);
        if (poll != null && poll.getStatus() == PollStatus.ACTIVE) {
            synchronized (poll) { // Synchronize updates to ensure thread-safety
                poll.setQuestion(newQuestion);
            }
            return true;
        }
        return false;
    }

    public Future<Boolean> voteOnPollAsync(String pollId, String option) {
        return executorService.submit(() -> { // Submit voting task to thread pool
            Poll poll = pollStore.get(pollId);
            if (poll != null) {
                return poll.vote(option);
            }
            return false;
        });
    }

    public Map<String, Integer> getPollResults(String pollId) {
        Poll poll = pollStore.get(pollId);
        if (poll != null && poll.getStatus() == PollStatus.CLOSED) {
            Map<String, Integer> results = new HashMap<>();
            poll.getOptions().forEach((key, value) -> results.put(key, value.get())); // Copy results safely
            return results;
        }
        return Collections.emptyMap();
    }

    public boolean closePoll(String pollId) {
      Poll poll = pollStore.get(pollId);
      if (poll != null) {
          poll.setStatus(PollStatus.CLOSED); // Close the poll
          return true;
      }
      return false;
  }

    public void shutdown() {
        executorService.shutdown();
    }
}


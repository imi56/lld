package com.practice.polling;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;

public class Poll {
    private final String pollId;
    private volatile String question; // `volatile` ensures visibility across threads
    private final ConcurrentHashMap<String, AtomicInteger> options; // Thread-safe options
    private volatile PollStatus status;

    public Poll(String pollId, String question, List<String> options) {
        this.pollId = pollId;
        this.question = question;
        this.options = new ConcurrentHashMap<>();
        for (String option : options) {
            this.options.put(option, new AtomicInteger(0));
        }
        this.status = PollStatus.ACTIVE;
    }

    public boolean vote(String option) {
        if (status != PollStatus.ACTIVE || !options.containsKey(option)) {
            return false;
        }
        options.get(option).incrementAndGet(); // Atomic increment
        return true;
    }

    public ConcurrentHashMap<String, AtomicInteger> getOptions() {
        return options;
    }

    public String getPollId() {
        return pollId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public PollStatus getStatus() {
        return status;
    }

    public void setStatus(PollStatus status) {
        this.status = status;
    }
}


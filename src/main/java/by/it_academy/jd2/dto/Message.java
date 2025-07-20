package by.it_academy.jd2.dto;

import java.time.LocalDateTime;

public class Message {
    private LocalDateTime time;
    private String author;
    private String destination;
    private String text;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private Message(LocalDateTime time, String author, String destination, String text) {
        this.time = time;
        this.author = author;
        this.destination = destination;
        this.text = text;
    }

    public static class Builder {
        private LocalDateTime time;
        private String author;
        private String destination;
        private String text;
        public Builder setTime(LocalDateTime time) {
            this.time = time;
            return this;
        }
        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }
        public Builder setDestination(String destination) {
            this.destination = destination;
            return this;
        }
        public Builder setText(String text) {
            this.text = text;
            return this;
        }
        public Message build() {
            return new Message(time, author, destination, text);
        }
    }
}

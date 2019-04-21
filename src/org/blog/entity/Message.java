package org.blog.entity;

public class Message {
    private long message_id;
    private long message_user_id;
    private User user;
    private String message_contents;
    private String message_time;
    private int have_read;
    private long reply_id;

    public long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(long message_id) {
        this.message_id = message_id;
    }

    public long getMessage_user_id() {
        return message_user_id;
    }

    public void setMessage_user_id(long message_user_id) {
        this.message_user_id = message_user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage_contents() {
        return message_contents;
    }

    public void setMessage_contents(String message_contents) {
        this.message_contents = message_contents;
    }

    public String getMessage_time() {
        return message_time;
    }

    public void setMessage_time(String message_time) {
        this.message_time = message_time;
    }

    public int getHave_read() {
        return have_read;
    }

    public void setHave_read(int have_read) {
        this.have_read = have_read;
    }

    public long getReply_id() {
        return reply_id;
    }

    public void setReply_id(long reply_id) {
        this.reply_id = reply_id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message_id=" + message_id +
                ", message_user_id=" + message_user_id +
                ", user=" + user +
                ", message_contents='" + message_contents + '\'' +
                ", message_time='" + message_time + '\'' +
                ", have_read=" + have_read +
                ", reply_id=" + reply_id +
                '}';
    }
}

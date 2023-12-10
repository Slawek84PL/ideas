package pl.slawek.ideas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private String title;
    private String content;

    //info
    //error

    public static Message info(String message) {
        return new Message("Info", message);
    }

    public static Message error(String message) {
        return new Message("Error", message);
    }

}

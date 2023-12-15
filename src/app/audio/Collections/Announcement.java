package app.audio.Collections;

import app.audio.Files.AudioFile;
import lombok.Getter;
import lombok.Setter;

public class Announcement {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;

    public Announcement(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

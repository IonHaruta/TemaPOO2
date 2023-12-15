package app.user;

import app.Admin;
import app.audio.Collections.Album;
import app.audio.Collections.Announcement;
import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import fileio.input.CommandInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Host extends User{
    @Getter
    @Setter
    private ArrayList<Podcast> podcasts;
    @Getter
    @Setter
    private ArrayList<Announcement> announcements;
    public Host(String username, int age, String city, String type) {
        super(username, age, city, type);
        podcasts = new ArrayList<>();
        announcements = new ArrayList<>();
    }

    public String addPodcast(final CommandInput commandInput) {
        Podcast podcast = new Podcast(commandInput.getName(),
                commandInput.getUsername(), commandInput.getEpisodes());
        for (Podcast podcastHost : this.podcasts) {
            if (podcastHost.getName().contains(commandInput.getName())) {
                return commandInput.getUsername() + " has another podcast with the same name.";
            }
        }

        Set<String> uniquePodcast = new HashSet<>();
        for (Episode episode : podcast.getEpisodes()) {
            String episodeName = episode.getName();
            if (!uniquePodcast.add(episodeName)) {
                return commandInput.getUsername()
                        + " has the same episode in this podcast.";
            }
        }

        this.podcasts.add(podcast);
        Admin.addPodcast(podcast);
        return commandInput.getUsername() + " has added new podcast successfully.";
    }
    public String addAnnouncement(final CommandInput commandInput) {
        Announcement announcement = new Announcement(commandInput.getName(),
                commandInput.getDescription());
        for (Announcement announcementHost : this.announcements) {
            if (announcementHost.getName().contains(commandInput.getName())) {
                return commandInput.getUsername() + " has another announcement with the same name.";
            }
        }

        this.announcements.add(announcement);
        Admin.getAnnouncements().add(announcement);
        return commandInput.getUsername() + " has successfully added new announcement.";
    }
    public String removeAnnouncement(final CommandInput commandInput) {
        for (Announcement announcementHost : this.announcements) {
            if (announcementHost.getName().equals(commandInput.getName())) {
                announcements.remove(announcementHost);
                return commandInput.getUsername() + " has successfully deleted the announcement.";
            }
        }
        return commandInput.getUsername() + " has no announcement with the given name.";
    }
}

package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpotifySearchResult {
	private String id;
	private String name;
    private String type;
    private String imageUrl;
    private String externalUrl;
    private int durationMs;
    
    public String getFormattedDuration() {
        int seconds = (durationMs / 1000) % 60;
        int minutes = (durationMs / 1000) / 60;
        return String.format("%d:%02d", minutes, seconds);
    }
  
}

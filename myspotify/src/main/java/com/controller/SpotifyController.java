package com.controller;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.SpotifySearchResult;

@Controller
public class SpotifyController {


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String home() {
        return "index";
    }

//    @GetMapping("/profile")
//    public String profile(OAuth2AuthenticationToken auth, Model model) {
//        String name = auth.getPrincipal().getAttribute("display_name");
//        model.addAttribute("name", name);
//        return "profile";
//    }

//    @GetMapping("/searchtracks")
//    public String searchSpotify(@RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient client,
//                                OAuth2AuthenticationToken authToken,
//                                Model model,
//                                @RequestParam("query") String query) {  // <-- accept query from user
//
//        String accessToken = client.getAccessToken().getTokenValue();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(accessToken);
//        HttpEntity<?> entity = new HttpEntity<>(headers);
//
//        String encodedQuery = UriUtils.encode(query, StandardCharsets.UTF_8);
//        String url = "https://api.spotify.com/v1/search?q=" + encodedQuery + "&type=track&limit=5";
//
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//
//        List<SpotifySearchResult> results = new ArrayList<>();
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode root = mapper.readTree(response.getBody());
//            JsonNode tracks = root.path("tracks").path("items");
//
//            for (JsonNode item : tracks) {
//                String name = item.path("name").asText();
//                String type = item.path("type").asText();
//                String imageUrl = item.path("album").path("images").get(0).path("url").asText();
//                String externalUrl = item.path("external_urls").path("spotify").asText();
//
//                results.add(new SpotifySearchResult(name, type, imageUrl, externalUrl));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        model.addAttribute("results", results);
//        model.addAttribute("searchQuery", query); // optional: show user what they searched
//        return "searchResults";
//    }
//



    
//    @GetMapping("/profile")
//    public String profile(OAuth2AuthenticationToken auth,
//                          @RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient client,
//                          Model model,
//                          @RequestParam(name = "query", required = false) String query) {
//
//        String name = auth.getPrincipal().getAttribute("display_name");
//        model.addAttribute("name", name);
//
//        if (query != null && !query.isBlank()) {
//            String accessToken = client.getAccessToken().getTokenValue();
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setBearerAuth(accessToken);
//            HttpEntity<?> entity = new HttpEntity<>(headers);
//
//            String encodedQuery = UriUtils.encode(query, StandardCharsets.UTF_8);
//            String url = "https://api.spotify.com/v1/search?q=" + encodedQuery + "&type=track&limit=10";
//
//            try {
//                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//                ObjectMapper mapper = new ObjectMapper();
//                JsonNode root = mapper.readTree(response.getBody());
//                JsonNode tracks = root.path("tracks").path("items");
//
//                List<SpotifySearchResult> results = new ArrayList<>();
//
//                for (JsonNode item : tracks) {
//                    String id = item.path("id").asText(); // 🔹 Extract track ID
//                    String trackName = item.path("name").asText();
//                    String type = item.path("type").asText();
//                    String imageUrl = "";
//                    JsonNode images = item.path("album").path("images");
//                    if (images.isArray() && images.size() > 0) {
//                        imageUrl = images.get(0).path("url").asText();
//                    }
//                    String externalUrl = item.path("external_urls").path("spotify").asText();
//
//                    // 🔹 Pass id into constructor
//                    results.add(new SpotifySearchResult(id, trackName, type, imageUrl, externalUrl));
//                }
//
//                model.addAttribute("results", results);
//                model.addAttribute("searchQuery", query);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return "profile"; // JSP page
//    }
//
//    
//    
//    @GetMapping("/saved-tracks")
//    public String getSavedTracks(Model model,
//                                 OAuth2AuthenticationToken authToken,
//                                 @RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient client) {
//        String accessToken = client.getAccessToken().getTokenValue();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(accessToken);
//        HttpEntity<?> entity = new HttpEntity<>(headers);
//
//        String url = "https://api.spotify.com/v1/me/tracks?limit=10"; // You can change the limit
//
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//
//        List<SpotifySearchResult> results = new ArrayList<>();
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode root = mapper.readTree(response.getBody());
//            JsonNode items = root.path("items");
//
//            for (JsonNode item : items) {
//                JsonNode track = item.path("track");
//                String id = track.path("id").asText(); 
//                String name = track.path("name").asText();
//                String type = track.path("type").asText();
//                String imageUrl = track.path("album").path("images").get(0).path("url").asText();
//                String externalUrl = track.path("external_urls").path("spotify").asText();
//
//                results.add(new SpotifySearchResult(id,name, type, imageUrl, externalUrl));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        model.addAttribute("results", results);
//        model.addAttribute("title", "Your Saved Tracks");
//        return "savedTracks"; // create savedTracks.jsp
//    }
    
    @GetMapping("/profile")
    public String profile(OAuth2AuthenticationToken auth,
                          @RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient client,
                          Model model,
                          @RequestParam(name = "query", required = false) String query) {

        String name = auth.getPrincipal().getAttribute("display_name");
        model.addAttribute("name", name);

        if (query != null && !query.isBlank()) {
            String accessToken = client.getAccessToken().getTokenValue();

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);
            HttpEntity<?> entity = new HttpEntity<>(headers);

            String encodedQuery = UriUtils.encode(query, StandardCharsets.UTF_8);
            String url = "https://api.spotify.com/v1/search?q=" + encodedQuery + "&type=track&limit=10";

            try {
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.getBody());
                JsonNode tracks = root.path("tracks").path("items");

                List<SpotifySearchResult> results = new ArrayList<>();

                for (JsonNode item : tracks) {
                    String id = item.path("id").asText();
                    String trackName = item.path("name").asText();
                    String type = item.path("type").asText();
                    int durationMs = item.path("duration_ms").asInt();

                    String imageUrl = "";
                    JsonNode images = item.path("album").path("images");
                    if (images.isArray() && images.size() > 0) {
                        imageUrl = images.get(0).path("url").asText();
                    }

                    String externalUrl = item.path("external_urls").path("spotify").asText();

                    results.add(new SpotifySearchResult(id, trackName, type, imageUrl, externalUrl, durationMs));
                }

                model.addAttribute("results", results);
                model.addAttribute("searchQuery", query);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "profile"; // JSP page
    }


    @GetMapping("/saved-tracks")
    public String getSavedTracks(Model model,
                                 OAuth2AuthenticationToken authToken,
                                 @RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient client) {
        String accessToken = client.getAccessToken().getTokenValue();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = "https://api.spotify.com/v1/me/tracks?limit=10";

        List<SpotifySearchResult> results = new ArrayList<>();
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode items = root.path("items");

            for (JsonNode item : items) {
                JsonNode track = item.path("track");
                String id = track.path("id").asText();
                String name = track.path("name").asText();
                String type = track.path("type").asText();
                int durationMs = track.path("duration_ms").asInt();

                String imageUrl = "";
                JsonNode images = track.path("album").path("images");
                if (images.isArray() && images.size() > 0) {
                    imageUrl = images.get(0).path("url").asText();
                }

                String externalUrl = track.path("external_urls").path("spotify").asText();

                results.add(new SpotifySearchResult(id, name, type, imageUrl, externalUrl, durationMs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("results", results);
        model.addAttribute("title", "Your Saved Tracks");
        return "savedTracks"; // View
    }


    
    @PostMapping("/likeTrack")
    public String likeTrack(@RequestParam("trackId") String trackId,
                            @RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient client,
                            RedirectAttributes redirectAttributes) {

        String accessToken = client.getAccessToken().getTokenValue();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        try {
            String url = "https://api.spotify.com/v1/me/tracks?ids=" + trackId;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
            System.out.println();
            redirectAttributes.addFlashAttribute("message", "Track saved successfully!");
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to save track: " + e.getMessage());
        }

        return "redirect:/profile";
    }

    
    @PostMapping("/removeTrack")
    public String removeTrack(@RequestParam("trackId") String trackId,
                              @RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient client,
                              RedirectAttributes redirectAttributes) {

        String accessToken = client.getAccessToken().getTokenValue();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.set("Content-Type", "application/json");  // Set correct content type

        // JSON body with the track ID
        String body = "{\"ids\":[\"" + trackId + "\"]}";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        try {
            String url = "https://api.spotify.com/v1/me/tracks";
            restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
            redirectAttributes.addFlashAttribute("message", "Track removed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to remove track: " + e.getMessage());
        }

        return "redirect:/saved-tracks";
    }
    
   
}

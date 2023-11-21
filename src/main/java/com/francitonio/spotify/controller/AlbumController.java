package com.francitonio.spotify.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francitonio.spotify.client.Album;
import com.francitonio.spotify.client.AlbumSpotifyClient;
import com.francitonio.spotify.client.AuthSpotifyClient;
import com.francitonio.spotify.client.LoginRequest;

@RestController
@RequestMapping("/spotify/api")
public class AlbumController {

	private final AuthSpotifyClient authSpotifyClient;
	private final AlbumSpotifyClient albumSpotifyClient;

	public AlbumController(AuthSpotifyClient authSpotifyClient, AlbumSpotifyClient albumSpotifyClient) {
		this.authSpotifyClient = authSpotifyClient;
		this.albumSpotifyClient = albumSpotifyClient;
	}

	@GetMapping("/albums")
	public ResponseEntity<List<Album>> helloWorld() {

		var request = new LoginRequest("client_credentials", "seu_client_id", "seu_client_secret");
		var token = authSpotifyClient.login(request).getAccessToken();

		var response = albumSpotifyClient.getReleases("Bearer " + token);

		return ResponseEntity.ok(response.getAlbums().getItems());
	}
}
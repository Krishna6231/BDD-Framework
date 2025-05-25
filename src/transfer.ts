// src/transfer.ts
import { getSpotifyPlaylistSongs } from './spotify';

export interface TransferOptions {
  spotifyUrl: string;
//   spotifyEmail: string;
//   spotifyPass: string;
//   ytmusicEmail: string;
//   ytmusicPass: string;
}

export async function transferPlaylist(options: TransferOptions): Promise<void> {
  console.log("🔐 Logging into Spotify...");
  const songs = await getSpotifyPlaylistSongs(options.spotifyUrl);


  console.log("🎵 Songs extracted:");
  songs.forEach((song, idx) => {
    console.log(`${idx + 1}. ${song.title} - ${song.artist}`);
  });

  // In next step: add to YouTube Music
}

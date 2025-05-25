import { chromium } from 'playwright';
import readline from 'readline';
import { transferPlaylist } from './transfer';

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const ask = (question: string): Promise<string> => {
  return new Promise(resolve => rl.question(question, resolve));
};

(async () => {
  const spotifyUrl = await ask("Enter Spotify Playlist URL: ");
  const spotifyEmail = await ask("Spotify Email: ");
  const spotifyPass = await ask("Spotify Password: ");
  const ytmusicEmail = await ask("YouTube Music Email: ");
  const ytmusicPass = await ask("YouTube Music Password: ");

  rl.close();

  await transferPlaylist({
    spotifyUrl,
    
  });
})();

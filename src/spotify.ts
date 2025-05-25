import { Browser, Page, chromium } from 'playwright';
import { delay } from './utils';

export interface Song {
  title: string;
  artist: string;
}

export async function getSpotifyPlaylistSongs(playlistUrl: string): Promise<Song[]> {
  const browser: Browser = await chromium.launch({
    headless: false,
    slowMo: 50,
  });

  const context = await browser.newContext({
    viewport: { width: 1280, height: 800 },
  });

  const page: Page = await context.newPage();

  try {
    // 1. Open playlist directly (no login)
    await page.goto(playlistUrl, { waitUntil: 'networkidle' });
    await page.waitForSelector('div[data-testid="tracklist-row"]');

    // 2. Auto-scroll to load all songs
    // Scroll the inner container (the scrollable one)
let lastCount = 0;
let stableTries = 0;

while (true) {
  const count = await page.$$eval('div[data-testid="tracklist-row"]', els => els.length);

  if (count === lastCount) {
    stableTries++;
  } else {
    lastCount = count;
    stableTries = 0;
  }

  if (stableTries >= 3 || count >= 1000) break;

  await page.evaluate(() => {
    const scrollContainer = document.querySelector('.os-viewport');
    if (scrollContainer) {
      scrollContainer.scrollBy(0, 1000); // scroll down inside the container
    }
  });

  await delay(1000); // adjust delay to fine-tune performance
}


    // 3. Extract songs
    const songs: Song[] = await page.$$eval('div[data-testid="tracklist-row"]', rows =>
      rows.map(row => {
        const title = row.querySelector('div[aria-colindex="2"] a[href^="/track"]')?.textContent?.trim() || '';
        const artists = Array.from(row.querySelectorAll('a[href^="/artist"]'))
          .map(a => a.textContent?.trim())
          .filter(Boolean)
          .join(', ');
        return { title, artist: artists };
      })
    );

    console.log(`✅ Extracted ${songs.length} songs.`);
    return songs;
  } catch (err) {
    console.error('❌ Error extracting songs:', err);
    return [];
  } finally {
    await browser.close();
  }
}

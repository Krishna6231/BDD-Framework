export const delay = (ms: number) => new Promise(resolve => setTimeout(resolve, ms));

export const typeLikeHuman = async (el: any, text: string) => {
  for (const char of text) {
    await el.type(char);
    await delay(100 + Math.random() * 150); // random delay per keystroke
  }
};

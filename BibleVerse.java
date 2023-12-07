import java.util.ArrayList;
public class BibleVerse {
    private String book;
    private int chapter;
    private int verse;
    private String text;
    private String version;
    private ArrayList<Partner> mates;
 

    public BibleVerse(String book, int chapter, int verse, String text, String version, ArrayList<Partner> mates) {
        this.book = book;
        this.chapter = chapter;
        this.verse = verse;
        this.text = text;
        this.version = version;
        this.mates = mates;
    }

    public String getBook() {
        return book;
    }

    public String getVersion() {
        return version;
    }

    public int getChapter() {
        return chapter;
    }

    public int getVerse() {
        return verse;
    }

    public String getText() {
        return text;
    }

    public ArrayList<Partner> getMates() {
        return mates;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BEGIN_VERSE\r\n"); // Add a marker
        stringBuilder.append("Book: ").append(book).append("\r\n");
        stringBuilder.append("Chapter: ").append(chapter).append("\r\n");
        stringBuilder.append("Verse: ").append(verse).append("\r\n");
        stringBuilder.append("Text: ").append(text).append("\r\n");
        stringBuilder.append("Version: ").append(version).append("\r\n");
    
        if (mates != null && !mates.isEmpty()) {
            stringBuilder.append("Memorization Partners:\r\n");
            for (Partner mate : mates) {
                stringBuilder.append("- ").append(mate.getPartnerType()).append(": ").append(mate.getBibleVerse()).append("\r\n");
            }
        } else {
            stringBuilder.append("No Memorization Partners\r\n");
        }
    
        return stringBuilder.toString();
    }

   
}

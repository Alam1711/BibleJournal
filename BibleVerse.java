import java.util.ArrayList;
public class BibleVerse {
    private String book;
    private String chapter;
    private String verse;
    private String text;
    private String version;
    private ArrayList<Partner> mates;
 

    public BibleVerse(String book, String chapter, String verse, String text, String version, ArrayList<Partner> mates) {
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

    public String getChapter() {
        return chapter;
    }

    public String getVerse() {
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
        stringBuilder.append(book);
        stringBuilder.append(":").append(chapter);
        stringBuilder.append(":").append(verse);
        stringBuilder.append(":").append(text);
        stringBuilder.append(":").append(version);
    
        if (mates != null && !mates.isEmpty()) {
            
            for (Partner mate : mates) {
                stringBuilder.append(":").append(mate.getPartnerType());
            }

        }
    
        return stringBuilder.toString();
    }

   
}

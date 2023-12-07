import java.util.ArrayList;
public class Partner {
    private String partnerType;
    private String bibleVerse;
    private boolean complete;

    public Partner(String partnerT, String bibleVerse, boolean complete) {
        this.partnerType = partnerT;
        this.bibleVerse = bibleVerse;
        this.complete = complete;
    }

    // Getter for partnerType
    public String getPartnerType() {
        return partnerType;
    }

    // Setter for partnerType
    public void setPartnerType(String partnerType) {
        this.partnerType = partnerType;
    }

    // Getter for bibleVerse
    public String getBibleVerse() {
        return bibleVerse;
    }

    // Setter for bibleVerse
    public void setBibleVerse(String bibleVerse) {
        this.bibleVerse = bibleVerse;
    }

    // Getter for complete
    public boolean isComplete() {
        return complete;
    }

    // Setter for complete
    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}

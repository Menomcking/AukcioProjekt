import java.time.LocalDateTime;

public class Festmeny {
    private String cim;
    private String festo;
    private String stilus;
    private int licitekSzama;
    private int legmagasabbLicit;
    private LocalDateTime legutolsoLicitIdeje;
    private boolean elkelt;

    public Festmeny(String cim, String festo, String stilus) {
        this.cim = cim;
        this.festo = festo;
        this.stilus = stilus;
    }

    public Festmeny(String row) {
        var s = row.split(";");
        this.cim = s[0];
        this.festo = s[1];
        this.stilus = s[2];
    }

    public Festmeny(Prompter prompter) { // interactive constructor cause why the fuck not
        this.cim = prompter.prompt("Title");
        this.festo = prompter.prompt("Painter");
        this.stilus = prompter.prompt("Style");
    }

    public String getFesto() {
        return festo;
    }

    public String getStilus() {
        return stilus;
    }

    public int getLicitekSzama() {
        return licitekSzama;
    }

    public int getLegmagasabbLicit() {
        return legmagasabbLicit;
    }

    public LocalDateTime getLegutolsoLicitIdeje() {
        return legutolsoLicitIdeje;
    }

    public boolean getElkelt() {
        return elkelt;
    }

    public void setElkelt(boolean elkelt) {
        this.elkelt = elkelt;
    }

    private static int truncateLicit(int n) {
        var mul = (int)Math.pow(10, (int)Math.floor(Math.log10(n)) - 1);
        return (n / mul) * mul;
    }

    public void licit(int mertek) {
        if (elkelt) {
            System.err.println("Auction is closed.");
            return;
        }

        double perc = (mertek + 100) / 100.0;
        int newAmount = 100;

        if (licitekSzama > 0) {
            newAmount = truncateLicit((int)(legmagasabbLicit * perc));
        }

        licitekSzama++;
        legmagasabbLicit = newAmount;
        legutolsoLicitIdeje = LocalDateTime.now();
    }

    public void licit() { licit(10); }

    @Override
    public String toString() {
        return String.format("%s: %s (%s)\n%s\n%d$ - %s (összesen: %d db)\n",
                festo, cim, stilus,
                elkelt ? "elkelt" : "nem kelt el",
                legmagasabbLicit,
                legutolsoLicitIdeje == null ? "meg nem volt licit" : legutolsoLicitIdeje.toString(),
                licitekSzama);
    }


}
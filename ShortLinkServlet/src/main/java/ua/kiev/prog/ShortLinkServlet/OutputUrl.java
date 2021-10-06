package ua.kiev.prog.ShortLinkServlet;

public class OutputUrl extends InputUrl {
    private String shortUrl;
    private String comment;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
